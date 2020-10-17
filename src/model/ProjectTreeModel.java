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
 * [1] (Thompson, 2020)
 */

public class ProjectTreeModel implements TreeModel {
    private final Project rootProject;
    private final Vector<TreeModelListener> treeModelListeners = new Vector<>();

    public ProjectTreeModel(Project rootProject) {
        this.rootProject = rootProject;
    }

    @Override
    public Object getRoot() {
        return rootProject;
    }

    @Override
    public Object getChild(Object parent, int index) { return ((TreeNode) parent).getChildAt(index); }

    @Override
    public int getChildCount(Object parent) {
        return ((TreeNode) parent).getChildCount();
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((TreeNode) node).isLeaf();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) { }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return ((TreeNode) parent).getIndex((TreeNode) child);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        treeModelListeners.addElement(l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        treeModelListeners.remove(l);
    }

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
