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

    /**
     * Constructor used to add necessary text and components to the panels on left hand side
     * of the frame, used to accept data from the user
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

        JLabel projectLabel = new JLabel(title, SwingConstants.CENTER);
        projectLabel.setBounds(0, 2, width, 15);
        projectLabel.setForeground(colour);
        projectLabel.setFocusable(false);
        this.add(projectLabel);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(5, 20, FIELD_START - 2, 15);
        titleLabel.setFocusable(false);
        this.add(titleLabel);
        titleEntry = new JTextField();
        titleEntry.setBounds(FIELD_START, 20, 200, 20);
        this.add(titleEntry);
        
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(5, 40, FIELD_START - 2, 15);
        descriptionLabel.setFocusable(false);
        this.add(descriptionLabel);
        descriptionEntry = new JTextField();
        descriptionEntry.setBounds(FIELD_START, 40, 340, 20);
        this.add(descriptionEntry);


    }
}
