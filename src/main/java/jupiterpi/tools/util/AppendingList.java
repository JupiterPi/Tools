package jupiterpi.tools.util;

import java.util.ArrayList;

public class AppendingList extends ArrayList<String> {
    public String render(String separator) {
        return ToolsUtil.appendWithSeparator(this, separator);
    }
}