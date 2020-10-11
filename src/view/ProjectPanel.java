package view;

import java.awt.*;

/**
 * @author manmohansingh
 * @version 11/10/2020 12:23
 */

public class ProjectPanel extends AbstractProjectTaskPanel{
    public static final int MIN_WIDTH = 450;
    public static final int MIN_HEIGHT = 125;

    public ProjectPanel(String title, int width, int height, Color colour) {
        super(title, Math.max(MIN_WIDTH, width), Math.max(MIN_HEIGHT, height), colour);
    }

}
