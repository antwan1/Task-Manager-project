package view;

import model.Model;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION;

/**
 * @author manmohansingh
 * @version 13/10/2020 10:56
 */

public class ProjectTreePanel extends JPanel{

    private final Model model;
    public static final int MIN_WIDTH = 200;
    public static final int MIN_HEIGHT = 200;
    private final JLabel panelLabel;
    private final JScrollPane scrollPane;
    private final JButton modifyButton;
    private final JTree projectTree;
    private final JButton findButton;
    private final JButton deleteButton;
    private final JButton completeButton;

    /**
     * Constructor to create a panel to display projects and tasks in tree format.
     *
     * @param title parameter inheriting the title to the tree panel
     * @param width parameter assigning the width to the tree panel
     * @param height parameter assigning the height to the tree panel
     * @param colour parameter assigning the colour to the text in the panel
     */

    public ProjectTreePanel(String title, Model model, int width, int height, Color colour) {
        this.model = model;
        this.setLayout(null);
        this.setSize(width, height);
        this.setBorder(BorderFactory.createLineBorder(colour));
        
        panelLabel = new JLabel(title, SwingConstants.CENTER);
        panelLabel.setBounds(0, 2, width, 15);
        panelLabel.setForeground(colour);
        panelLabel.setFocusable(false);
        this.add(panelLabel);
        
        projectTree = new JTree(model.getProjectTreeModel());
        projectTree.setRootVisible(false);
        projectTree.getSelectionModel().setSelectionMode(SINGLE_TREE_SELECTION);
        projectTree.addTreeSelectionListener(this::treeSelection);
        scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setView(projectTree);
        scrollPane.setBounds(5, 20, width - 10, height - 48);
        this.add(scrollPane);

        modifyButton = new JButton("Modify");
        modifyButton.setBounds((width / 2) - 95, height - 25, 60, 20);
        modifyButton.setEnabled(false);
        this.add(modifyButton);

        findButton = new JButton("Find");
        findButton.setBounds((width / 2) - 30, height - 25, 60, 20);
        this.add(findButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds((width / 2) + 35, height - 25, 60, 20);
        this.add(deleteButton);
        deleteButton.addActionListener(new Action());

        completeButton = new JButton("Complete");
        completeButton.setBounds((width / 2) - 100, height - 25, 60,20);
        this.add(completeButton);
        completeButton.addActionListener(new Action());

    }

    private void treeSelection(TreeSelectionEvent treeSelectionEvent) {
        TreeNode selectedItem = (TreeNode) projectTree.getLastSelectedPathComponent();
        modifyButton.setEnabled(selectedItem != null);
        model.setSelection(selectedItem);
    }

    /**
     * @author haresh
     * This class will allow the user to press the delete button and
     * a pop-up message to come in a new panel to corroborate with the
     * user whether they want to delete the chosen file or go back to
     * the main frame.
     */
    static class Action implements ActionListener {

        public void actionPerformed (ActionEvent e){

            JFrame deleteFrame = new JFrame("Delete");
            deleteFrame.setVisible(true);
            deleteFrame.setSize(400,200);
            JLabel prompt = new JLabel("Are you sure you want to delete this?");
            JPanel deletePanel = new JPanel();
            deleteFrame.add(deletePanel);
            deletePanel.add(prompt);

//          JButton backButton = new JButton("Back");
//          backButton.setBounds(50, 22, 60, 20);
//          backButton.setEnabled(true);
//          backButton.setVisible(true);
//          backButton.setSize(40,20);
//          backButton.add(backButton);
//          backButton.addActionListener();



        }
    }

    @Override
    protected void paintComponent(Graphics adj){
        super.paintComponent(adj);
        panelLabel.setSize(this.getWidth(), 15);
        scrollPane.setSize(this.getWidth() - 10, this.getHeight() - 48);
        modifyButton.setBounds((this.getWidth() / 2) - 95, this.getHeight() - 25, 60, 20);
        findButton.setBounds((this.getWidth() / 2) - 30, this.getHeight() - 25, 60, 20);
        deleteButton.setBounds((this.getWidth() / 2) + 35, this.getHeight() - 25, 60, 20);
    }
}
