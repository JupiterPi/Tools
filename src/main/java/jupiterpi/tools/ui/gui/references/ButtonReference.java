package jupiterpi.tools.ui.gui.references;

import javax.swing.*;
import java.awt.*;

public class ButtonReference extends ComponentReference {
    private final JButton button = (JButton) component;

    public ButtonReference(JButton component) {
        super(component);
    }

    public String getLabel() {
        return button.getText();
    }

    public void setLabel(String label) {
        button.setText(label);
    }

    public void click() {
        button.doClick();
    }
}
