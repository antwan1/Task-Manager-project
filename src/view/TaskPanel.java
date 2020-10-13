package view;

import javax.swing.*;
import java.awt.*;

//Added TaskPanel, extended it from abstractProjectTaskPanel (superclass), adding importance and estimated time.
public class TaskPanel extends  AbstractProjectTaskPanel {
    public static final int MIN_WIDTH = 450;
    public static final int MIN_HEIGHT = 125;

    public TaskPanel(String title, int width, int height, Color colour) {
        super(title, Math.max(MIN_WIDTH, width), Math.max(MIN_HEIGHT, height), colour);

        JLabel importance = new JLabel("importance:");
        importance.setBounds(5, 100, 98, 15);
        importance.setFocusable(false);
        this.add(importance);




    }



 }
