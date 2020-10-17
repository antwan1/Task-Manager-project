package view;

import javax.swing.*;
import java.awt.*;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class ErrorPanel extends JPanel{
    public static final int MIN_WIDTH = 100;
    public static final int MIN_HEIGHT = 100;
    private final JLabel paneLabel;
    private final JScrollPane scrollPane;

    public ErrorPanel(int width, int height, ListModel<String> errorMessages) {
        setLayout(null);
        this.setSize(width, height);
        this.setBorder(BorderFactory.createLineBorder(Color.RED));
        this.setFocusable(false);

        paneLabel = new JLabel("Errors", SwingConstants.CENTER);
        paneLabel.setBounds(5, 2, width - 10, 15);
        paneLabel.setForeground(Color.RED);
        paneLabel.setFocusable(false);
        this.add(paneLabel);

        new JList<>(errorMessages).setFocusable(false);

        scrollPane = new JScrollPane(VERTICAL_SCROLLBAR_AS_NEEDED,
                HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setView(new JList<>(errorMessages));
        scrollPane.setBounds(5, 20, width - 10, height - 25);
        this.add(scrollPane);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        paneLabel.setSize(this.getWidth(), 15);
        scrollPane.setSize(this.getWidth() - 10, this.getHeight() - 35);
    }
}
