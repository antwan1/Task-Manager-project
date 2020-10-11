import view.AppFrame;
import javax.swing.*;

/**
 * This segment includes the parent class to the complete task management system
 * The program when compiled initiates from this program, Hence the name "Start"
 * @author manmohansingh
 * @version 11/10/2020 10:40
 */

public class Start {
    /**
     * The purpose of this standard program is to run GUI application based on JavaSwing library
     * Thus, creating a thread to run the application
     * @param ptm parameters passed to the program on initiation
     * [1] (Thompson, 2020)
     */
    public static void main(String[] ptm) {
        SwingUtilities.invokeLater(() -> new AppFrame());
    }
}
