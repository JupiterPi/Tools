package jupiterpi.tools.files.csv;

import jupiterpi.tools.files.Path;
import jupiterpi.tools.files.TextFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An extension of CSVFile for directly handling simple key-value-pairs.
 * @see CSVFile
 */
public class CSVStringMapFile extends CSVFile {
    /**
     * Creates a new CSVStringMap object.
     * @param path The path to the CSV file.
     * @see Path
     */
    public CSVStringMapFile(Path path) {
        super(path);
    }

    /**
     * Creates a new CSVStringMap object.
     * @param file The CSV file.
     */
    public CSVStringMapFile(TextFile file) {
        super(file);
    }

    /**
     * Returns the generated key-value-pairs.
     * @return The generated key-value-pairs.
     */
    public Map<String, String> getStringMap() {
        Map<String, String> map = new HashMap<>();
        for (String[] parts : get()) {
            map.put(parts[0], parts[1]);
        }
        return map;
    }

    /**
     * Writes new key-value-pairs to the CSV file.
     * @param map The key-value-pairs to the CSV file.
     */
    public void writeStringMap(Map<String, String> map) {
        List<String[]> keyValuePairsList = new ArrayList<>();
        for (String key : map.keySet()) {
            keyValuePairsList.add(new String[] {key, map.get(key)});
        }
        write(keyValuePairsList);
    }
}
