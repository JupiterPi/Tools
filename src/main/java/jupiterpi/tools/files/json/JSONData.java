package jupiterpi.tools.files.json;

import org.json.simple.*;

/**
 * As basic class that reads JSON data.
 */
public class JSONData {
    /**
     * The JSON data as text.
     */
    protected String text;

    /**
     * Creates a new JSONData object.
     * @param text The text to generate the JSON data from.
     */
    public JSONData(String text) {
        this.text = text;
    }

    /**
     * Returns the JSON data.
     * @return The JSON data.
     */
    public JSONObject get() {
        return (JSONObject) JSONValue.parse(text);
    }
}
