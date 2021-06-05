package jupiterpi.tools.files.csv;

import jupiterpi.tools.files.Path;
import jupiterpi.tools.files.TextFile;

import java.util.ArrayList;
import java.util.List;

/**
 * An extension of CSVData for directly handling files.
 * @see CSVData
 */
public class CSVFile extends CSVData {
    /**
     * The path to the CSV file.
     * @see Path
     */
    protected Path filePath;

    /**
     * Creates a new CSVFile object.
     * @param path The path to the CSV file.
     * @see Path
     */
    public CSVFile(Path path, boolean allowCreate) {
        super(new TextFile(path, allowCreate).getFile());
        filePath = path;
    }

    /**
     * Creates a new CSVFile object.
     * @param path The path to the CSV file.
     * @see Path
     */
    public CSVFile(Path path) {
        super(new TextFile(path).getFile());
        filePath = path;
    }

    /**
     * Writes new content to the CSV file.
     * @param linesParts The lines, represented by an array of strings between the regexes.
     */
    public void write(List<String[]> linesParts) {
        TextFile file = new TextFile(filePath);
        List<String> lines = new ArrayList<>();
        for (String[] lineParts : linesParts) {
            String line = "";
            for (String attribute : lineParts) {
                if (attribute.contains(REGEX)) System.err.println("Character " + REGEX + " not allowed: is regex. ");
                line += attribute + REGEX;
            }
            line = line.substring(0, line.length()-1);
            lines.add(line);
        }
        file.setFile(lines);
        file.saveFile();
    }
}
