package jupiterpi.tools.ui.gui;

import jupiterpi.tools.ui.gui.references.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class representing a window for the application. It allows you to create labels, buttons and input fields.
 */
public class Window {
    private JFrame w;
    private JPanel panel;

    /**
     * Creates a swing window for the application.
     * @param title The title of the window.
     */
    public Window(String title, boolean isMainWindow) {
        prepareWindow(title, isMainWindow);
        w.setSize(300, 200);
    }

    protected Window(boolean isFileChooser) {
        if (isFileChooser) {
            prepareWindow("Choose a File", false);
            JFileChooser fileChooser = new JFileChooser();
            panel.add(fileChooser);
            w.setSize(550, 400);
        } else prepareWindow("Application", true);
    }

    private void prepareWindow(String title, boolean isMainWindow) {
        w = new JFrame();
        w.setTitle(title);
        w.setLocationRelativeTo(null);
        if (isMainWindow) w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        else w.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        panel = new JPanel();
        w.setContentPane(panel);
    }

    /**
     * Adds a label to the window.
     * @param text The text in the label.
     */
    public LabelReference addLabel(String text) {
        JLabel label = new JLabel(text);
        panel.add(label);
        return new LabelReference(label);
    }

    /**
     * Adds a button to the window.
     * @param text The text in the button.
     * @param onClick A Runnable object with the code to execute, when the button is pressed.
     */
    public ButtonReference addButton(String text, Runnable onClick) {
        JButton button = new JButton(text);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClick.run();
            }
        });
        panel.add(button);
        return new ButtonReference(button);
    }

    /**
     * Adds an input field to the window.
     */
    public InputFieldReference addInputField() {
        JTextField field = new JTextField();
        field.setColumns(5);
        panel.add(field);
        return new InputFieldReference(field);
    }

    /**
     * Adds a label and an input field to the window.
     * @param label The text in the label.
     */
    public ComponentReference[] addInputField(String label) {
        ComponentReference[] references = new ComponentReference[2];
        references[0] = addLabel(label);
        references[1] = addInputField();
        return references;
    }

    /**
     * Adds a text area to the window.
     */
    public TextAreaReference addTextArea() {
        JTextArea textArea = new JTextArea();
        panel.add(textArea);
        return new TextAreaReference(textArea);
    }

    /*
    // browse
    public void addFileChooser(String label) {
        addButton(label, new Runnable() {
            @Override
            public void run() {
                Window fileChooserWindow = new Window(true);
                fileChooserWindow.show();
            }
        });
    }

     */

    /**
     * Shows the window.
     */
    public void show() {
        w.setVisible(true);
    }
}

// important components: label, button, input, textarea, browser