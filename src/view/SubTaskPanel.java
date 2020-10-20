package view;

import model.Model;
import model.data.Project;

import javax.swing.tree.TreeNode;
import java.awt.*;
import java.util.Calendar;

public class SubTaskPanel extends AbstractProjectTaskPanel {
    /**
     * Constructor used to add necessary text and components to the panels on left hand side
     * of the frame, used to accept model.model.model.data from the user
     *
     * @param title  adding titles to the panel
     * @param model
     * @param width  assigning the pre-defined width to the panel
     * @param height assigning the pre-defined height to the panel
     * @param colour assigning the colour to the text associated with the panel
     */
    public SubTaskPanel(String title, Model model, int width, int height, Color colour) {
        super(title, model, width, height, colour);
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
