package model;

import model.data.Project;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import java.util.Arrays;
import java.util.Vector;

/**
 * @author manmohansingh
 * 17/10/2020 08:50
 *
 * [1] (Thompson, 2020)
 */

public class ProjectsComboboxModel implements ComboBoxModel<Project> , TreeModelListener {

    private final Model model;
    private final ProjectTreeModel projectsTree;
    private Vector<ListDataListener> listDataListeners = new Vector<>();
    private Project selectedItem;
    private Project[] projects;

    public ProjectsComboboxModel(Model model) {
        this.model = model;
        this.projectsTree = (ProjectTreeModel) model.getProjectTreeModel();
        this.projectsTree.addTreeModelListener(this);
        projects = model.getProjects().values().toArray(new Project[0]);
        Arrays.sort(projects);
        setSelectedItem(projects[0]);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = (Project) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    @Override
    public int getSize() {
        return model.getProjects().size();
    }

    @Override
    public Project getElementAt(int index) {
        return projects[index];
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listDataListeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) { listDataListeners.remove(l); }

    private void fireProjectsChanged() {
        ListDataEvent event = new ListDataEvent(projects, ListDataEvent.CONTENTS_CHANGED,
                0, projects.length);
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
        projects = model.getProjects().values().toArray(new Project[0]);
        Arrays.sort(projects);
        setSelectedItem(selectedItem);
        fireProjectsChanged();
    }
}
