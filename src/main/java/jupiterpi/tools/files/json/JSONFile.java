package jupiterpi.tools.files.json;

import jupiterpi.tools.files.Path;
import jupiterpi.tools.files.TextFile;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * An extension of JSONData for directly handling files.
 * @see JSONData
 */
public class JSONFile extends JSONData {
    /**
     * The JSON file.
     */
    protected TextFile file;

    /**
     * Creates a new JSONFile from a file.
     * @param file The file to generate the JSONFile from.
     */
    public JSONFile(TextFile file) {
        super(file.getContentContinuum());
        this.file = file;
    }

    /**
     * Creates a new JSONFile from a path.
     * @param path The path to the JSON file.
     * @see Path
     */
    public JSONFile(Path path) {
        super(new TextFile(path).getContentContinuum());
        this.file = new TextFile(path);
    }

    /**
     * Writes new content to the JSON file.
     * @param content The content to write to the JSON file.
     */
    public void write(JSONObject content) {
        String text = content.toJSONString();
        List<String> lines = new ArrayList<>();
        lines.add(text);
        file.setFile(lines);
        file.saveFile();
    }
}
