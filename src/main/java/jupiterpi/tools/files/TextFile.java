package jupiterpi.tools.files;

import java.util.*;
import java.io.*;

/**
 * A (very old) tool for simply accessing files.
 */
public class TextFile {
    /**
     * The name of the file you want to access.
     */
    protected File file;

    /**
     * All the lines on the file.
     */
    protected List<String> lines = new ArrayList();

    /**
     * Whether the file should automatically be saved when editing lines.
     */
    protected boolean autosave = false;

    /* constructors */

    /**
     * Creates a new TextFile.
     * @param file The name of the file.
     */
    public TextFile(String file) {
        try {
            read(new File(file), false);
        } catch (DoesNotExistException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new TextFile.
     * @param file The file the TextFile shall represent.
     */
    public TextFile(File file) {
        try {
            read(file, false);
        } catch (DoesNotExistException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new TextFile.
     * @param path The path to the file.
     */
    public TextFile(Path path) {
        try {
            read(path.file(), false);
        } catch (DoesNotExistException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new TextFile.
     * @param file The name of the file.
     */
    public TextFile(String file, boolean allowCreate) throws DoesNotExistException {
        read(new File(file), allowCreate);
    }

    /**
     * Creates a new TextFile.
     * @param file The file the TextFile shall represent.
     */
    public TextFile(File file, boolean allowCreate) throws DoesNotExistException {
        read(file, allowCreate);
    }

    /**
     * Creates a new TextFile.
     * @param path The path to the file.
     */
    public TextFile(Path path, boolean allowCreate) throws DoesNotExistException {
        read(path.file(), allowCreate);
    }

    /* autosave */

    /**
     * Sets whether the file should automatically be saved when editing lines.
     * @param autosave Whether the file should automatically be saved when editing lines.
     * @return The text file object again.
     */
    public TextFile autosave(boolean autosave) {
        this.autosave = autosave;
        return this;
    }

    /**
     * Sets that the file should automatically be saved when editing lines.
     * @return The text file object again.
     */
    public TextFile autosave() {
        this.autosave = true;
        return this;
    }

    protected void read(File file, boolean allowCreate) throws DoesNotExistException {
        this.file = file;
        if (!file.exists()) {
            if (allowCreate) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                throw new DoesNotExistException();
            }
        }
        try {
            read(new BufferedReader(new FileReader(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void read(BufferedReader bufferedReader) {
        try {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class DoesNotExistException extends Exception {
        public DoesNotExistException() {
            super("The file specified for this TextFile object does not exist. ");
        }
    }

    /* getters */

    /**
     * Gives you the lines of the file.
     * @return The lines.
     */
    public List<String> getFile() {
        return lines;
    }

    /**
     * Gives you a single line. Equivalent to fileTool.getLines().get.
     * @param line The index of the line.
     * @return The line.
     */
    public String getLine(int line) {
        return lines.get(line);
    }

    /**
     * Gives you the file with each line separated by a newline character.
     * @return The file's content.
     */
    public String getFileForOutput() {
        String str = "";
        for (String line : lines) {
            str += line + "\n";
        }
        if (str.length()>0) str = str.substring(0, str.length()-1);
        return str;
    }

    /**
     * Gives you the file with all lines directly appended.
     * @return The file's content.
     */
    public String getContentContinuum() {
        String str = "";
        for (String line : lines) {
            str += line;
        }
        return str;
    }

    /* setters */

    /**
     * Resets the content of the file.
     * @param lines The new lines.
     */
    public void setFile(List<String> lines) {
        this.lines = lines;
        as();
    }

    /**
     * Resets a single line.
     * @param index The line's index.
     * @param text The new content of the line.
     */
    public void setLine(int index, String text) {
        lines.set(index, text);
        as();
    }

    /**
     * Appends a string to the end of a line.
     * @param index The index of the line.
     * @param text The text to append.
     */
    public void writeToLine(int index, String text) {
        this.setLine(index, this.getLine(index) + text);
        as();
    }

    /**
     * Adds a line to the file.
     * @param text The line to add.
     */
    public void addLine(String text) {
        lines.add(text);
        as();
    }

    protected void as() {
        if (autosave) saveFile();
    }

    /* others */

    /**
     * Saves the file.
     */
    public void saveFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}