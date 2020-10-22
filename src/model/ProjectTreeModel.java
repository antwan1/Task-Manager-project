package model;

import model.data.Project;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.Vector;

/**
 * @author manmohansingh
 * 17/10/2020 11:00
 *
 * (Thompson, 2020)
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

public class ProjectTreeModel implements TreeModel {
    private final Project rootProject;
    private final Vector<TreeModelListener> treeModelListeners = new Vector<>();

    /**
     * Constructor to initialise root Project to the variable.
     *
     * @param rootProject stores the root project
     */
    public ProjectTreeModel(Project rootProject) {
        this.rootProject = rootProject;
    }

    /**
     * Returns the root of the tree.  Returns <code>null</code>
     * only if the tree has no nodes.
     *
     * @return  the root of the tree
     */
    @Override
    public Object getRoot() {
        return rootProject;
    }

    /**
     * Returns the child of <code>parent</code> at index <code>index</code>
     * in the parent's
     * child array.  <code>parent</code> must be a node previously obtained
     * from this data source. This should not return <code>null</code>
     * if <code>index</code>
     * is a valid index for <code>parent</code> (that is <code>index &gt;= 0 &amp;&amp;
     * index &lt; getChildCount(parent</code>)).
     *
     * @param parent    a node in the tree, obtained from this data source
     * @param index     index of child to be returned
     * @return          the child of {@code parent} at index {@code index}
     */
    @Override
    public Object getChild(Object parent, int index) { return ((TreeNode) parent).getChildAt(index); }

    /**
     * Returns the number of children of <code>parent</code>.
     * Returns 0 if the node
     * is a leaf or if it has no children.  <code>parent</code> must be a node
     * previously obtained from this data source.
     *
     * @param   parent  a node in the tree, obtained from this data source
     * @return  the number of children of the node <code>parent</code>
     */
    @Override
    public int getChildCount(Object parent) {
        return ((TreeNode) parent).getChildCount();
    }

    /**
     * Returns <code>true</code> if <code>node</code> is a leaf.
     * It is possible for this method to return <code>false</code>
     * even if <code>node</code> has no children.
     * A directory in a filesystem, for example,
     * may contain no files; the node representing
     * the directory is not a leaf, but it also has no children.
     *
     * @param   node  a node in the tree, obtained from this data source
     * @return  true if <code>node</code> is a leaf
     */
    @Override
    public boolean isLeaf(Object node) {
        return ((TreeNode) node).isLeaf();
    }

    /**
     * Messaged when the user has altered the value for the item identified
     * by <code>path</code> to <code>newValue</code>.
     * If <code>newValue</code> signifies a truly new value
     * the model should post a <code>treeNodesChanged</code> event.
     *
     * @param path path to the node that the user has altered
     * @param newValue the new value from the TreeCellEditor
     */
    @Override
    public void valueForPathChanged(TreePath path, Object newValue) { }

    /**
     * Returns the index of child in parent.  If either <code>parent</code>
     * or <code>child</code> is <code>null</code>, returns -1.
     * If either <code>parent</code> or <code>child</code> don't
     * belong to this tree model, returns -1.
     *
     * @param parent a node in the tree, obtained from this data source
     * @param child the node we are interested in
     * @return the index of the child in the parent, or -1 if either
     *    <code>child</code> or <code>parent</code> are <code>null</code>
     *    or don't belong to this tree model
     */
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return ((TreeNode) parent).getIndex((TreeNode) child);
    }

    /**
     * Adds a listener for the <code>TreeModelEvent</code>
     * posted after the tree changes.
     *
     * @param   l       the listener to add
     * @see     #removeTreeModelListener
     */
    @Override
    public void addTreeModelListener(TreeModelListener l) {
        treeModelListeners.addElement(l);
    }

    /**
     * Removes a listener previously added with
     * <code>addTreeModelListener</code>.
     *
     * @see     #addTreeModelListener
     * @param   l       the listener to remove
     */
    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        treeModelListeners.remove(l);
    }

    /**
     * An indicator function used to inform the tree about the update
     *
     * @param addedProject TreeNode which has been updated
     */

    public void fireTreeNodeAdded(TreeNode addedProject) {
        ArrayList<TreeNode> path = new ArrayList<>();
        path.add(rootProject);
        for (TreeNode parent = addedProject.getParent();
            parent != rootProject;
            parent = parent.getParent()) {
            path.add(1, parent);
        }
        final int[] indices = {addedProject.getParent().getIndex(addedProject)};
        TreeModelEvent e = new TreeModelEvent(this, path.toArray(), indices,
                new Object[] {addedProject});
        for (TreeModelListener ml : treeModelListeners) {
            ml.treeStructureChanged(e);
        }
    }
}
