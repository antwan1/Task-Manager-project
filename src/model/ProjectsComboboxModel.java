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
 * ********************************************************************
 * Title: TemperatureRecording
 * Author: Thompson, E (@thompel1)
 * Date: 2020
 * Code Version: N/A
 * Availability: https://gitlab.com/FoOOSD/temperaturerecording.git
 * ********************************************************************
 * [Source Code] https://gitlab.com/FoOOSD/temperaturerecording.git
 *
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

    /**
     * Set the selected item. The implementation of this  method should notify
     * all registered <code>ListDataListener</code>s that the contents
     * have changed.
     *
     * @param anItem the list object to select or <code>null</code>
     *        to clear the selection
     */
    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = (Project) anItem;
    }

    /**
     * Returns the selected item
     * @return The selected item or <code>null</code> if there is no selection
     */
    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    /**
     * Returns the length of the list.
     * @return the length of the list
     */
    @Override
    public int getSize() {
        return model.getProjects().size();
    }

    /**
     * Returns the value at the specified index.
     * @param index the requested index
     * @return the value at <code>index</code>
     */
    @Override
    public Project getElementAt(int index) {
        return projects[index];
    }

    /**
     * Adds a listener to the list that's notified each time a change
     * to the data model occurs.
     * @param l the <code>ListDataListener</code> to be added
     */
    @Override
    public void addListDataListener(ListDataListener l) {
        listDataListeners.add(l);
    }

    /**
     * Removes a listener from the list that's notified each time a
     * change to the data model occurs.
     * @param l the <code>ListDataListener</code> to be removed
     */
    @Override
    public void removeListDataListener(ListDataListener l) { listDataListeners.remove(l); }

    private void fireProjectsChanged() {
        ListDataEvent event = new ListDataEvent(projects, ListDataEvent.CONTENTS_CHANGED,
                0, projects.length);
        for(ListDataListener listDataListener : listDataListeners) {
            listDataListener.contentsChanged(event);
        }
    }

    /**
     * <p>Invoked after a node (or a set of siblings) has changed in some
     * way. The node(s) have not changed locations in the tree or
     * altered their children arrays, but other attributes have
     * changed and may affect presentation. Example: the name of a
     * file has changed, but it is in the same location in the file
     * system.
     *
     * <p>To indicate the root has changed, childIndices and children
     * will be null.
     *
     * <p>Use {@code e.getPath()} to get the parent of the changed node(s).
     * {@code e.getChildIndices()} returns the index(es) of the changed node(s).
     *
     * @param e a {@code TreeModelEvent} describing changes to a tree model
     */
    @Override
    public void treeNodesChanged(TreeModelEvent e) {
        treeStructureChanged(e);
    }

    /**
     * <p>Invoked after nodes have been inserted into the tree.</p>
     *
     * <p>Use {@code e.getPath()} to get the parent of the new node(s).
     * {@code e.getChildIndices()} returns the index(es) of the new node(s)
     * in ascending order.
     *
     * @param e a {@code TreeModelEvent} describing changes to a tree model
     */
    @Override
    public void treeNodesInserted(TreeModelEvent e) {
        treeStructureChanged(e);
    }

    /**
     * <p>Invoked after nodes have been removed from the tree.  Note that
     * if a subtree is removed from the tree, this method may only be
     * invoked once for the root of the removed subtree, not once for
     * each individual set of siblings removed.</p>
     *
     * <p>Use {@code e.getPath()} to get the former parent of the deleted
     * node(s). {@code e.getChildIndices()} returns, in ascending order, the
     * index(es) the node(s) had before being deleted.
     *
     * @param e a {@code TreeModelEvent} describing changes to a tree model
     */
    @Override
    public void treeNodesRemoved(TreeModelEvent e) {
        treeStructureChanged(e);
    }

    /**
     * <p>Invoked after the tree has drastically changed structure from a
     * given node down.  If the path returned by e.getPath() is of length
     * one and the first element does not identify the current root node
     * the first element should become the new root of the tree.
     *
     * <p>Use {@code e.getPath()} to get the path to the node.
     * {@code e.getChildIndices()} returns null.
     *
     * @param e a {@code TreeModelEvent} describing changes to a tree model
     */
    @Override
    public void treeStructureChanged(TreeModelEvent e) {
        projects = model.getProjects().values().toArray(new Project[0]);
        Arrays.sort(projects);
        setSelectedItem(selectedItem);
        fireProjectsChanged();
    }
}
