package jupiterpi.tools.ui.gui;

import javax.swing.*;

/**
 * A shortcuts class to javax.swing.JOptionPane.
 */
public class Popups {
    /**
     * Shows a message dialog from JOptionPane.showMessageDialog().
     * @param message The message in the dialog.
     */
    public static void alert(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Shows an input dialog from JOptionPane.showInputDialog().
     * @param message The message in the dialog.
     * @return The user's input.
     */
    public static String input(String message) {
        return JOptionPane.showInputDialog(message);
    }

    /**
     * Shows a confirm dialog from JOptionPane.showConfirmDialog().
     * @param message The message in the dialog.
     * @return If the user has clicked yes or no.
     */
    public static boolean confirm(String message) {
        int answer = JOptionPane.showConfirmDialog(null, message, null, JOptionPane.YES_NO_OPTION);
        return answer == JOptionPane.YES_OPTION;
    }
}
