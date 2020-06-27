package jupiterpi.tools.ui.gui.references;

import java.awt.*;

public class ComponentReference {
    protected Component component;

    public ComponentReference(Component component) {
        this.component = component;
    }

    public Component getComponent() {
        return component;
    }
}
