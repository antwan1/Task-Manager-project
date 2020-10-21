package view;

import model.Model;
import model.data.Project;

import javax.swing.tree.TreeNode;
import java.awt.*;
import java.util.Calendar;

/**
 * @author manmohansingh
 * @version 11/10/2020 12:23
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

public class ProjectPanel extends AbstractProjectTaskPanel{
    public static final int MIN_WIDTH = 450;
    public static final int MIN_HEIGHT = 105;
    private Project treeNode;

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
            Project project = (Project) treeNode;
            titleEntry.setText(project.getTitle());
            descriptionEntry.setText(project.getDescription());
            dueDateModel.setValue(project.getDueDate());
            System.out.println(treeNode);
        }
    }

    @Override
    protected void addProjectTask(String title, String description, Project parent, Calendar dueDate) {
        model.addProject(title, description, parent, dueDate);
    }

    @Override
    protected void modifyProjectTask(String title, String description, Project parent, Calendar dueDate) {
        model.modifyProject(title, description, parent, dueDate);
    }

}
