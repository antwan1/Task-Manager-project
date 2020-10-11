package view;

import java.awt.*;
import javax.swing.*;

/**
 * @author manmohansingh
 * @version 11/10/2020 10:53
 */

public class AppFrame extends JFrame{
    private final int minWidth = 500;
    private final int minHeight = 500;
    private int height = 600;
    private int width = 1000;

    /**
     * Constructor used to call functions in order to create an environment to run
     * the application.
     * [1] Thompson, 2020
     */
    public AppFrame()throws HeadlessException {
        super("Project & Task Manager");
        mainframe();
        framelayout();
        this.setVisible(true);
    }

    /**
     * The following function is used to set the basic form size, position of the application window
     * on screen and its behaviours such as closing and resize
     */
    private void mainframe() {
        getContentPane().setLayout(null);
        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
        this.setMinimumSize(new Dimension(minWidth, minHeight));
        this.setMaximumSize(new Dimension(screenDimensions.width - 50, screenDimensions.height - 50));
        if (height > screenDimensions.height) {
            height = screenDimensions.height;
        }
        if (width > screenDimensions.width) {
            width = screenDimensions.width;
        }
        this.setLocation((screenDimensions.height - height) / 2, (screenDimensions.width - width) / 2);
        this.setSize(width, height);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
    }

    private void framelayout() {

    }
}
