package jupiterpi.tools.ui.gui.references;

import javax.swing.*;

public class TextAreaReference extends ComponentReference {
    private JTextArea field;

    public TextAreaReference(JTextArea component) {
        super(component);
    }

    public String getInput() {
        return field.getText();
    }

    public void setInput(String input) {
        field.setText(input);
    }
}
