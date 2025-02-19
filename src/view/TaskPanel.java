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

        importanceSelect = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));
        importanceSelect.setBounds(FIELD_START, 98, 80, 20);
        importanceSelect.setFocusable(true);
        this.add(importanceSelect);

        JLabel imp_instruct = new JLabel("(0 = Low, 99 = High)");
        imp_instruct.setBounds(190, 102, 140, 15);
        imp_instruct.setFocusable(false);
        this.add(imp_instruct);
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
    protected void addProjectTask(String title, String description, Project parent, Calendar dueDate) {
        model.addTask(title, description, parent, dueDate, (Integer) importanceSelect.getValue());
    }

    @Override
    protected void modifyProjectTask(String title, String description, Project parent, Calendar dueDate) {
    }
}
