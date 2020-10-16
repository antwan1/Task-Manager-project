package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author manmohansingh
 * @version 13/10/2020 20:17
 */

public class TaskPanel extends AbstractProjectTaskPanel{
    public static final int MIN_WIDTH = 450;
    public static final int MIN_HEIGHT = 160;

    public TaskPanel(String title, int width, int height, Color colour) {
        super(title, Math.max(MIN_WIDTH, width), Math.max(MIN_HEIGHT, height), colour);

        JLabel importance = new JLabel("Importance (0 to 99): ");
        importance.setBounds(5, 120, 200, 15);
        importance.setFocusable(false);
        this.add(importance);

    }
}
