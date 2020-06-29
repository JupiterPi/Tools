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
    private String fileName;

    /**
     * All the lines on the file.
     */
    private List<String> lines = new ArrayList();

    /**
     * Creates a new TextFile.
     * @param fileName The name of the file.
     */
    public TextFile(String fileName) {
        initialize(new File(fileName));
    }

    /**
     * Creates a new TextFile.
     * @param file The file the TextFile shall represent.
     */
    public TextFile(File file) {
        initialize(file);
    }

    /**
     * Creates a new TextFile.
     * @param path The path to the file.
     */
    public TextFile(Path path) {
        initialize(path.file());
    }

    private void initialize(File file) {
        this.fileName = file.getName();
        try {
            BufferedReader Reader = new BufferedReader(new FileReader(file));
            boolean fileEnd = false;

            while (!fileEnd) {
                String line = Reader.readLine();
                if (line == null) fileEnd = true;
                else lines.add(line);
            }
            Reader.close();
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    /**
     * Gives you the lines of the file.
     * @return The lines.
     */
    public List<String> getFile() {
        return lines;
    }

    /**
     * Gives you the file with each line separated by a newline character.
     * @return The file's content.
     */
    public String getFileForOutput() {
        String returning = "";
        for (int i = 0; i < lines.size(); i++) {
            if (i != lines.size() - 1) {
                returning += lines.get(i) + "\n";
            } else {
                returning += lines.get(i);
            }
        }
        return returning;
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

    /**
     * Resets the lines of the file.
     * @param newFile The new lines.
     */
    public void setFile(List newFile) {
        this.lines = newFile;
    }

    /**
     * Gives you a single line. Equivalent to fileTool.getLines().get.
     * @param line The index of the line.
     * @return The line.
     */
    public String getLine(int line) {
        return (String) lines.get(line);
    }

    /**
     * Resets a single line.
     * @param line The line's index.
     * @param text The new content of the line.
     */
    public void setLine(int line, String text) {
        lines.set(line, text);
    }

    /**
     * Appends a string to the end of a line.
     * @param line The index of the line.
     * @param text The text to append.
     */
    public void writeToLine(int line, String text) {
        this.setLine(line, this.getLine(line) + text);
    }

    /**
     * Adds a line to the file.
     * @param text The line to add.
     */
    public void addLine(String text) {
        lines.add(text);
    }

    /**
     * Saves the file.
     */
    public void saveFile() {
        try {
            String input;
            BufferedWriter Writer = new BufferedWriter(new FileWriter(fileName));
            for (int i = 0; i < lines.size(); i++) {
                Writer.write(lines.get(i) + "\r\n");
            }
            Writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}