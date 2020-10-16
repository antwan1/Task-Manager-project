package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author manmohansingh
 * @version 11/10/2020 12:26
 */

public class AbstractProjectTaskPanel extends JPanel {

    static final int FIELD_START = 100;
    private final JTextField titleEntry;
    private final JTextField descriptionEntry;
    private final JButton addButton;
    private final JComboBox<Object> parentEntry;

    /**
     * Constructor used to add necessary text and components to the panels on left hand side
     * of the frame, used to accept model.model.model.data from the user
     *
     * @param title adding titles to the panel
     * @param width assigning the pre-defined width to the panel
     * @param height assigning the pre-defined height to the panel
     * @param colour assigning the colour to the text associated with the panel
     *
     * [1] (Thompson, 2020)
     */

    public AbstractProjectTaskPanel(String title, int width, int height, Color colour) {
        setLayout(null);
        this.setSize(width, height);
        this.setBorder(BorderFactory.createLineBorder(colour));

        JLabel panelLabel = new JLabel(title, SwingConstants.CENTER);
        panelLabel.setBounds(0, 2, width, 15);
        panelLabel.setForeground(colour);
        panelLabel.setFocusable(false);
        this.add(panelLabel);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(5, 20, FIELD_START - 2, 15);
        titleLabel.setFocusable(false);
        this.add(titleLabel);
        titleEntry = new JTextField();
        titleEntry.setBounds(FIELD_START, 20, 240, 20);
        this.add(titleEntry);
        
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(5, 40, FIELD_START - 2, 15);
        descriptionLabel.setFocusable(false);
        this.add(descriptionLabel);
        descriptionEntry = new JTextField();
        descriptionEntry.setBounds(FIELD_START, 40, 340, 20);
        this.add(descriptionEntry);

        JLabel parentLabel = new JLabel("Parent: ");
        parentLabel.setBounds(5, 60, FIELD_START - 2, 15);
        parentLabel.setFocusable(false);
        this.add(parentLabel);
        parentEntry = new JComboBox<>();
        parentEntry.setBounds(FIELD_START, 60, 240, 20);
        this.add(parentEntry);

        JLabel creationDate = new JLabel("Date & Time: ");
        creationDate.setBounds(5, 80, FIELD_START - 2, 15);
        creationDate.setFocusable(false);
        this.add(creationDate);

        JLabel dueDate = new JLabel("Due Date & Time: ");
        dueDate.setBounds(5, 100, FIELD_START + 15, 15);
        dueDate.setFocusable(false);
        this.add(dueDate);

        addButton = new JButton("Add");
        addButton.setBounds(width - 65, height - 25, 60, 20);
        addButton.setEnabled(true);
//        addButton.addActionListener(this::addProjectTask);
        this.add(addButton);
    }
}
