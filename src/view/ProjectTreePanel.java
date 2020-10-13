package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author manmohansingh
 * @version 13/10/2020 10:56
 */

public class ProjectTreePanel extends JPanel{

    public static final int MIN_WIDTH = 200;
    public static final int MIN_HEIGHT = 200;
    private final JLabel panelLabel;
    private final JScrollPane scrollPane;

    /**
     * Constructor to create a panel to display projects and tasks in tree format.
     *
     * @param title parameter inheriting the title to the tree panel
     * @param width parameter assigning the width to the tree panel
     * @param height parameter assigning the height to the tree panel
     * @param colour parameter assigning the colour to the text in the panel
     */

    public ProjectTreePanel(String title, int width, int height, Color colour) {
        this.setLayout(null);
        this.setSize(width, height);
        this.setBorder(BorderFactory.createLineBorder(colour));
        
        panelLabel = new JLabel(title, SwingConstants.CENTER);
        panelLabel.setBounds(0, 2, width, 15);
        panelLabel.setForeground(colour);
        panelLabel.setFocusable(false);
        this.add(panelLabel);
        
        JTree projectTree = new JTree();
        projectTree.setRootVisible(false);
        scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setView(projectTree);
        scrollPane.setBounds(5, 19, width - 10, height - 23);
        this.add(scrollPane);
    }

    @Override
    protected void paintComponent(Graphics adj){
        super.paintComponent(adj);
        panelLabel.setSize(this.getWidth(), 15);
        scrollPane.setSize(this.getWidth() - 10, this.getHeight() - 23);
    }
}
