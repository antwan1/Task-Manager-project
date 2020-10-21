package view;

import model.Model;
import model.data.Project;

import javax.swing.tree.TreeNode;
import java.awt.*;
import java.util.Calendar;

public class SubTaskPanel extends AbstractProjectTaskPanel {
    public static final int MIN_WIDTH = 450;
    public static final int MIN_HEIGHT = 125;
    
    public SubTaskPanel(String title, Model model, int width, int height, Color colour) {
        super(title, model, Math.max(MIN_WIDTH, width), Math.max(MIN_HEIGHT, height), colour);
    }

    @Override
    void treeSelectionChanged(TreeNode treeNode) {

    }

    @Override
    protected void addProjectTask(String title, String description, Project parent, Calendar dueDate) {

    }

    @Override
    protected void modifyProjectTask(String title, String description, Project parent, Calendar dueDate) {

    }
}
