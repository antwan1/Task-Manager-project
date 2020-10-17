package model;

import model.data.Task;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import java.util.Arrays;
import java.util.Vector;

/**
 * @author manmohansingh
 * 17/10/2020 09:35
 *
 * [1] (Thompson, 2020)
 */

public class TasksComboboxModel implements ComboBoxModel<Task>, TreeModelListener {

    private final Model model;
    private final ProjectTreeModel projectsTree;
    private Task[] tasks;
    private Vector<ListDataListener> listDataListeners = new Vector<>();
    private Task selectedItem;

    public TasksComboboxModel(Model model) {
        this.model = model;
        this.projectsTree = (ProjectTreeModel) model.getProjectTreeModel();
        this.projectsTree.addTreeModelListener(this);
        tasks = model.getTasks();
        Arrays.sort(tasks);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = (Task) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    @Override
    public int getSize() {
        return tasks.length;
    }

    @Override
    public Task getElementAt(int index) {
        return tasks[index];
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listDataListeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listDataListeners.remove(l);
    }

    private void fireProjectsChanged() {
        ListDataEvent event = new ListDataEvent(tasks, ListDataEvent.CONTENTS_CHANGED,
                0, tasks.length);
        for(ListDataListener listDataListener : listDataListeners) {
            listDataListener.contentsChanged(event);
        }
    }

    @Override
    public void treeNodesChanged(TreeModelEvent e) {
        treeStructureChanged(e);
    }

    @Override
    public void treeNodesInserted(TreeModelEvent e) {
        treeStructureChanged(e);
    }

    @Override
    public void treeNodesRemoved(TreeModelEvent e) {
        treeStructureChanged(e);
    }

    @Override
    public void treeStructureChanged(TreeModelEvent e) {
        tasks = model.getTasks();
        Arrays.sort(tasks);
        setSelectedItem(selectedItem);
        fireProjectsChanged();
    }

}
