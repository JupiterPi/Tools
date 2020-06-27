package jupiterpi.tools.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A basic reader for Streams.
 * @see InputStream
 */
public class StreamReader {
    /**
     * The corresponding InputStream object.
     * @see InputStream
     */
    private InputStream stream;

    /**
     * Creates a new StreamReader object.
     * @param stream The corresponding InputStream object.
     */
    public StreamReader(InputStream stream) {
        this.stream = stream;
    }

    /**
     * Reads the lines in the input stream.
     * @return The lines.
     * @throws IOException May occur when reading the lines.
     */
    public List<String> getLines() throws IOException {
        BufferedReader reader = getBufferedReader();
        List<String> lines = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

    /**
     * Reads the lines in the input stream and formats them for console output.
     * @return The formatted lines.
     * @throws IOException From getLines().
     */
    public String getMultilineString() throws IOException {
        List<String> lines = getLines();
        String str = "";
        for (String line : lines) {
            str += line + "\n";
        }
        str = str.substring(0, str.length()-1);
        return str;
    }

    /**
     * Reads the lines in the input stream and appends them to each other (without separator).
     * @return The appended lines.
     * @throws IOException From getLines().
     */
    public String getOneLineString() throws IOException {
        List<String> lines = getLines();
        String str = "";
        for (String line : lines) {
            str += line;
        }
        return str;
    }

    /**
     * Returns the corresponding BufferedReader.
     * @see BufferedReader
     * @return The BufferedReader.
     */
    private BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader(stream));
    }
}