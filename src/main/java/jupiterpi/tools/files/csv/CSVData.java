package jupiterpi.tools.files.csv;

import jupiterpi.tools.util.ToolsUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A basic class that reads CSV data.
 */
public class CSVData {
    /**
     * The lines of the CSV data.
     */
    protected List<String> lines;
    /**
     * The regex to split the lines at.
     */
    protected final String REGEX = ";";

    /**
     * Creates a new CSVData object.
     * @param lines The lines to work with.
     */
    public CSVData(List<String> lines) {
        this.lines = lines;
    }

    /**
     * Returns the splitted lines.
     * @return The splitted lines.
     */
    public List<String[]> get() {
        List<String[]> linesParts = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(REGEX);
            if (line.endsWith(REGEX)) {
                List<String> partsList = ToolsUtil.asArrayList(parts);
                partsList.add("");
                parts = partsList.toArray(new String[0]);
            }
            linesParts.add(parts);
        }
        return linesParts;
    }
}
