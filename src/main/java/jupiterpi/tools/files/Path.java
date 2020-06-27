package jupiterpi.tools.files;

import jupiterpi.tools.crossplatform.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A container with further methods for handling file system paths.
 */
public class Path {
    /**
     * The drive (only on Windows systems).
     */
    protected String drive;
    /**
     * The directories of the path, in order of hierarchy.
     */
    protected List<String> dirs = new ArrayList<>();
    /**
     * The file name (optional).
     */
    protected String fileName = null;

    /**
     * Creates a new Path object.
     * @param path The path, represented as a string (either formatted for Windows, Unix or Mac systems).
     */
    public Path(String path) {
        String[] parts;
        if (path.contains("/")) parts = path.split("/");
        else parts = path.split("\\\\");
        for (String part : parts) {
            if (part.contains(":")) drive = part;
            else if (part.contains(".")) fileName = part;
            else dirs.add(part);
        }
    }

    // basic setters and getters

    /**
     * Sets a new drive name (e. g. "C" or "D").
     * @param name The drive name.
     */
    public void setDrive(String name) {
        if (name.contains(":")) drive = name.toUpperCase();
        else drive = name.toUpperCase() + ":";
    }

    /**
     * Returns the drive name.
     * @return The drive name.
     */
    public String getDrive() {
        return drive;
    }

    /**
     * Returns the directories of the path, in order of hierarchy.
     * @return The directories.
     */
    public List<String> getDirs() {
        return dirs;
    }

    /**
     * Returns the lowest directory in the path.
     * @return The directory.
     */
    public String getLowestDir() {
        return dirs.get(dirs.size()-1);
    }

    /**
     * Returns the file name.
     * @return The file name.
     */
    public String getFileName() {
        return fileName;
    }

    // snake methods

    /**
     * Creates a copy of the path.
     */
    public Path copy() {
        return new Path(format());
    }

    /**
     * Goes to the parent directory of the current one.
     * @return The Path object for further coding.
     */
    public Path parent() {
        dirs.remove(dirs.size()-1);
        return this;
    }

    /**
     * Goes to a sub directory of the current one.
     * @param name The sub directory's name.
     * @return The Path object for further coding.
     */
    public Path subdir(String name) {
        dirs.add(name);
        return this;
    }

    /**
     * Sets the file name.
     * @param fileName The file name.
     * @return The Path object for further coding.
     */
    public Path file(String fileName) {
        this.fileName = fileName;
        return this;
    }

    // output and formatting

    /**
     * Generates a string, representing the Path object for console output.
     * @return The string, representing the Path object for console output.
     */
    public String toString() {
        return "Path (" + format(Platform.WINDOWS) + ")";
    }

    /**
     * Formats the path for Windows systems.
     * @return The formatted path.
     */
    public String win() {
        return format(Platform.WINDOWS);
    }

    /**
     * Formats the path for Unix systems.
     * @return The formatted path.
     */
    public String linux() {
        return format(Platform.LINUX);
    }

    /**
     * Formats the path for Mac systems.
     * @return The formatted path.
     */
    public String mac() {
        return format(Platform.MAC);
    }

    /**
     * Formats the path for the current platform.
     * @see jupiterpi.tools.crossplatform.Platforms
     * @return The formatted path.
     */
    public String format() {
        return format(Platforms.getCurrentPlatform());
    }

    /**
     * Formats the path.
     * @param platform The platform to format the path for.
     * @return The formatted path.
     */
    public String format(Platform platform) {
        return generateFormattedPath(drive, dirs, fileName, platform);
    }

    /**
     * Generates a formatted path with the given parameters.
     * @param drive The drive name.
     * @param dirs The directories in order of hierarchy.
     * @param fileName The file name.
     * @param platform The platform to format the path for.
     * @return The formatted path.
     */
    protected String generateFormattedPath(String drive, List<String> dirs, String fileName, Platform platform) {
        String str = "";
        String separator = "/";
        if (platform == Platform.WINDOWS) str += drive; separator = "\\";
        for (String dir : dirs) {
            str += separator + dir;
        }
        if (fileName != null) str += separator + fileName;
        return str;
    }

    /**
     * Generates a File object by using the Path object.
     * @return The File object.
     */
    public File file() {
        return new File(format());
    }

    /**
     * Generates a TextFile object by using the Path object.
     * @deprecated Better use TextFile(Path)
     * @return The TextFile object.
     */
    @Deprecated
    public TextFile textFile() {
        return new TextFile(format());
    }

    // static methods

    /**
     * Returns the directory the application is running in.
     * @return The directory the application is running in.
     */
    public static Path getRunningDirectory() {
        return new Path(System.getProperty("user.dir"));
    }

    // reading file hierarchy

    /**
     * Returns the sub directories inside the current path.
     * @return The sub directories.
     * @throws WrongPathTypeException If the path is currently pointing to a file the operation is not possible.
     */
    public List<Path> subdirs() throws WrongPathTypeException {
        return subs(true);
    }

    /**
     * Returns the sub files inside the current path.
     * @return The sub files.
     * @throws WrongPathTypeException If the path is currently pointing to a file the operation is not possible.
     */
    public List<Path> subfiles() throws WrongPathTypeException {
        return subs(false);
    }

    private List<Path> subs(boolean dirs) throws WrongPathTypeException {
        if (fileName != null) throw new WrongPathTypeException(WrongPathTypeException.PathType.FILE);
        File current = file();
        List<Path> subs = new ArrayList<>();
        for (File sub : current.listFiles()) {
            if(sub.isDirectory() == dirs) {
                Path subPath = this.copy();
                String name = sub.getName();
                if (dirs) subPath.subdir(name);
                else subPath.file(name);
                subs.add(subPath);
            }
        }
        return subs;
    }

    /**
     * Erases the file specification from the path to make it pointing to a directory.
     * @return The directory.
     */
    public Path dir() {
        return this.copy().file(null);
    }
}