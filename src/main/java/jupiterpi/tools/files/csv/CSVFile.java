package jupiterpi.tools.files.csv;

import jupiterpi.tools.files.TextFile;
import jupiterpi.tools.files.Path;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * An extension of CSVData for directly handling files.
 * @see CSVData
 */
public class CSVFile extends CSVData {
    /**
     * The CSV file.
     */
    protected File file;

    /**
     * Creates a new CSVFile from a file.
     * @param file The file to read the data from.
     */
    public CSVFile(File file) {
        super(new TextFile(file).getFile());
        this.file = file;
    }

    /**
     * Creates a new CSVFile from a path string.
     * @deprecated Use CSVFile(Path) instead.
     * @param path The string to generate the path to the file from.
     * @see Path
     */
    @Deprecated
    public CSVFile(String path) {
        super(new TextFile(new Path(path)).getFile());
        this.file = new Path(path).file();
    }

    /**
     * Creates a new CSVFile object.
     * @param path The path to the CSV file.
     * @see Path
     */
    public CSVFile(Path path) {
        super(new TextFile(path).getFile());
        this.file = path.file();
    }

    /**
     * Writes new content to the CSV file.
     * @param linesParts The lines, represented by an array of strings between the regexes.
     */
    public void write(List<String[]> linesParts) {
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
        TextFile file = new TextFile(this.file);
        file.setFile(lines);
        file.saveFile();
    }
}
