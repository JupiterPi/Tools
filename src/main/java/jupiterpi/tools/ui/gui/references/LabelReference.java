package jupiterpi.tools.ui.gui.references;

import javax.swing.*;

public class LabelReference extends ComponentReference {
    public LabelReference(JLabel component) {
        super(component);
    }

    public String getText() {
        return ((JLabel) component).getText();
    }

    public void setText(String text) {
        ((JLabel) component).setText(text);
    }
}