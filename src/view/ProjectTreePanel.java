package view;

import javax.swing.*;
import java.awt.*;

public class ProjectTreePanel extends JPanel{

    private final JLabel panelLabel;
    private final JScrollPane scrollPane;

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
}
