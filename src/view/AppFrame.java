package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author manmohansingh
 * @version 11/10/2020 10:53
 */

public class AppFrame extends JFrame{
    private final int minWidth = Math.max(TaskPanel.MIN_WIDTH, ProjectPanel.MIN_WIDTH) +
            ProjectTreePanel.MIN_WIDTH;
    private final int minHeight = Math.max(TaskPanel.MIN_HEIGHT, ProjectPanel.MIN_HEIGHT) +
            ProjectTreePanel.MIN_HEIGHT;
    private int height = 600;
    private int width = 1000;
    private ProjectTreePanel projects_and_tasks;
    private ProjectPanel project_entry;
    private TaskPanel task_entry;

    /**
     * Constructor used to call functions in order to create an environment to run
     * the application.
     * [1] (Thompson, 2020)
     */
    public AppFrame()throws HeadlessException {
        super("Project & Task Manager");
        mainFrame();
        frameLayout();
        this.setVisible(true);
    }

    /**
     * The following function is used to set the basic form size, position of the application window
     * on screen and its behaviours such as closing and resize
     */
    private void mainFrame() {
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

    /**
     * The following function is used to add different components to the main frame
     */
    private void frameLayout() {
        Container panel = this.getContentPane();
        final int leftColumnWidth = Math.max(ProjectPanel.MIN_WIDTH, 5);
        final int leftColumnEnd = this.width - leftColumnWidth - 5;
        projects_and_tasks = new ProjectTreePanel("Projects and Tasks",
                leftColumnEnd - 10, height - 30, Color.BLACK);
        projects_and_tasks.setLocation(460, 5);
        projects_and_tasks.setFocusable(false);
        panel.add(projects_and_tasks);

        project_entry = new ProjectPanel("Project Details", leftColumnWidth,
                ProjectPanel.MIN_HEIGHT, Color.BLACK);
        project_entry.setLocation(5, 5);
        project_entry.setFocusable(false);
        panel.add(project_entry);

        task_entry = new TaskPanel("Task Details", leftColumnWidth,
                TaskPanel.MIN_HEIGHT, Color.BLACK);
        task_entry.setLocation(5, ProjectPanel.MIN_HEIGHT + 10);
        task_entry.setFocusable(false);
        panel.add(task_entry);
    }

    @Override
    public void paint(Graphics adj){
        super.paintComponents(adj);
        if(this.getHeight() != height || this.getWidth() != width){
            height = this.getHeight();
            width = this.getWidth();
            final int leftColumnWidth = Math.max(ProjectPanel.MIN_WIDTH,
                    TaskPanel.MIN_WIDTH);
            final int leftColumnEnd = this.width - leftColumnWidth - 5;
            projects_and_tasks.setSize(leftColumnEnd - 10, height - 30);
            project_entry.setLocation(5, 5);
            task_entry.setLocation(5, ProjectPanel.MIN_HEIGHT + 10);
        }
    }
}
