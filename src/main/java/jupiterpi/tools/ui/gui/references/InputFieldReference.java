package jupiterpi.tools.ui.gui.references;

import javax.swing.*;
import java.awt.*;

public class InputFieldReference extends ComponentReference {
    protected JTextField field = (JTextField) component;

    public InputFieldReference(JTextField component) {
        super(component);
    }

    public String getInput() {
        return field.getText();
    }

    public void setInput(String input) {
        field.setText(input);
    }
}
