package view;

import model.Model;
import model.data.Project;
import model.data.Task;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.util.Calendar;

/**
 * @author manmohansingh
 * @version 13/10/2020 20:17
 */

public class TaskPanel extends AbstractProjectTaskPanel{
    public static final int MIN_WIDTH = 450;
    public static final int MIN_HEIGHT = 125;
    private final JSpinner importanceSelect;

    public TaskPanel(String title, Model model, int width, int height, Color colour) {
        super(title, model, Math.max(MIN_WIDTH, width), Math.max(MIN_HEIGHT, height), colour);

        JLabel importance = new JLabel("Importance: ");
        importance.setBounds(5, 100, FIELD_START - 2, 15);
        importance.setFocusable(false);
        this.add(importance);

        importanceSelect = new JSpinner(new SpinnerListModel(Model.IMP_SELECT));
        importanceSelect.setBounds(FIELD_START, 98, 80, 20);
        importanceSelect.setFocusable(true);
        this.add(importanceSelect);
    }

    @Override
    void treeSelectionChanged(TreeNode treeNode) {
        if (treeNode == null || treeNode.getClass() != Task.class) {
            System.out.println("Not modifying a Task");
        }
        else {
            System.out.println(treeNode);
        }
    }

    @Override
    protected void addProjectTask(String title, String description, Project parent) {
        model.addTask(title, description, parent);
    }

    @Override
    protected void modifyProjectTask(String title, String description, Project parent) {
    }
}
