package view;

import model.Model;
import model.data.Project;

import javax.swing.tree.TreeNode;
import java.awt.*;

/**
 * @author manmohansingh
 * @version 11/10/2020 12:23
 */

public class ProjectPanel extends AbstractProjectTaskPanel{
    public static final int MIN_WIDTH = 450;
    public static final int MIN_HEIGHT = 150;

    public ProjectPanel(String title, Model model, int width, int height, Color colour) {
        super(title, model, Math.max(MIN_WIDTH, width), Math.max(MIN_HEIGHT, height), colour);
    }

    @Override
    void treeSelectionChanged(TreeNode treeNode) {
        if (treeNode == null || treeNode.getClass() != Project.class) {
            addButton.setEnabled(true);
            addButton.setVisible(true);
            modifyButton.setEnabled(false);
            modifyButton.setVisible(false);
            System.out.println("Not modifying a project.");
        } else {
            addButton.setEnabled(false);
            addButton.setVisible(false);
            modifyButton.setEnabled(true);
            modifyButton.setVisible(true);
            System.out.println(treeNode);
        }
    }

    @Override
    protected void addProjectTask(String title, String description, Project parent) {
        model.addProject(title, description, parent);
    }

    @Override
    protected void modifyProjectTask(String title, String description, Project parent) {

    }

}
