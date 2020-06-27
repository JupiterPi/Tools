package jupiterpi.tools.files.csv;

/**
 * An interface used for types that are CSV castable.
 */
public interface CSVCastable {
    /**
     * A method to convert an object into CSV data.
     * @return The string to write on a CSV file.
     */
    public String[] toCSV();
}