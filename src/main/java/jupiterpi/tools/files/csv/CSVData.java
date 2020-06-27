package jupiterpi.tools.files.csv;

import java.util.ArrayList;
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
            linesParts.add(line.split(REGEX));
        }
        return linesParts;
    }
}
