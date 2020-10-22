package view;

import model.Model;
import model.data.Project;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilCalendarModel;

/**
 * @author manmohansingh
 * @version 11/10/2020 12:26
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

public abstract class AbstractProjectTaskPanel extends JPanel {
    protected final Model model;
    static final int FIELD_START = 100;
    protected final JTextField titleEntry;
    protected final JTextField descriptionEntry;
    protected final JButton addButton;
    private final JComboBox<Project> parentEntry;
    protected final JButton modifyButton;
    public final UtilCalendarModel dueDateModel;


    /**
     * Constructor used to add necessary text and components to the panels on left hand side
     * of the frame, used to accept model.data from the user
     *
     * @param title adding titles to the panel
     * @param width assigning the pre-defined width to the panel
     * @param height assigning the pre-defined height to the panel
     * @param colour assigning the colour to the text associated with the panel
     *
     */

    public AbstractProjectTaskPanel(String title, Model model, int width, int height, Color colour) {
        this.model = model;
        setLayout(null);
        this.setSize(width, height);
        this.setBorder(BorderFactory.createLineBorder(colour));

        JLabel panelLabel = new JLabel(title, SwingConstants.CENTER);
        panelLabel.setBounds(0, 2, width, 15);
        panelLabel.setForeground(colour);
        panelLabel.setFocusable(false);
        this.add(panelLabel);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(5, 22, FIELD_START - 2, 15);
        titleLabel.setFocusable(false);
        this.add(titleLabel);
        titleEntry = new JTextField();
        titleEntry.setBounds(FIELD_START, 18, 240, 20);
        this.add(titleEntry);
        
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(5, 40, FIELD_START - 2, 15);
        descriptionLabel.setFocusable(false);
        this.add(descriptionLabel);
        descriptionEntry = new JTextField();
        descriptionEntry.setBounds(FIELD_START, 38, 340, 20);
        this.add(descriptionEntry);

        JLabel parentLabel = new JLabel("Parent: ");
        parentLabel.setBounds(5, 60, FIELD_START - 2, 15);
        parentLabel.setFocusable(false);
        this.add(parentLabel);
        parentEntry = new JComboBox<>();
        parentEntry.setModel(model.getProjectComboBoxModel());
        parentEntry.setBounds(FIELD_START, 58, 240, 20);
        this.add(parentEntry);

        JLabel due_Date = new JLabel("Due Date: ");
        due_Date.setBounds(5, 80, FIELD_START - 2, 15);
        due_Date.setFocusable(false);
        this.add(due_Date);

        dueDateModel = new UtilCalendarModel();
        JDatePanelImpl dueDatePanel = new JDatePanelImpl(dueDateModel);
        JDatePickerImpl dueDatePicker = new JDatePickerImpl(dueDatePanel);
        dueDatePicker.setBounds(FIELD_START, 74, 150, 22);
        this.add(dueDatePicker);

        addButton = new JButton("Add");
        addButton.setBounds(width - 65, height - 25, 60, 20);
        addButton.setEnabled(true);
        addButton.addActionListener(this::addProjectTask);
        this.add(addButton);

        modifyButton = new JButton("Modify");
        modifyButton.setBounds(width - 65, height - 25, 60, 20);
        modifyButton.setEnabled(false);
        modifyButton.setVisible(false);
        modifyButton.addActionListener(this::modifyProjectTask);
        this.add(modifyButton);
        
        model.addSelectionListener(this::treeSelectionChanged);
    }

    abstract void treeSelectionChanged(TreeNode treeNode);

    /**
     * function called inside the project or task panel classes to add the entered details
     * for project or task by initiating a data listener.
     *
     * @param e is the action event when the listener is called i.e., when add button is pressed
     */
    private void addProjectTask(ActionEvent e) {
        Calendar dueDate = dueDateModel.getValue();
        String title = titleEntry.getText();
        String description = descriptionEntry.getText();
        Project parent = (Project) parentEntry.getSelectedItem();
        addProjectTask(title, description, parent, dueDate);
    }

    protected abstract void addProjectTask
            (String title, String description, Project parent, Calendar dueDate);

    /**
     * function called inside the project or task panel classes to modify the entered details
     * for project or task by initiating a data listener.
     *
     * @param actionEvent is the action event when the listener is called i.e, when modify button
     *                    is pressed.
     */
    private void modifyProjectTask(ActionEvent actionEvent) {
        Calendar dueDate = dueDateModel.getValue();
        String title = titleEntry.getText();
        String description = descriptionEntry.getText();
        Project parent = (Project) parentEntry.getSelectedItem();
        modifyProjectTask(title, description, parent, dueDate);
    }

    protected abstract void modifyProjectTask
            (String title, String description, Project parent, Calendar dueDate);
}
