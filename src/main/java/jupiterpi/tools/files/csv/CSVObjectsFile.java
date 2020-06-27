package jupiterpi.tools.files.csv;

import jupiterpi.tools.files.Path;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * An extension of CSVFile for directly handling lines that represent objects.
 * @see CSVFile
 * @param <T> The type of objects in the file.
 */
public class CSVObjectsFile<T extends CSVCastable> extends CSVFile {
    /**
     * The type of objects in the file.
     */
    private Class<T> clazz;

    /**
     * Creates a new CSVObjectsFile object.
     * @param file The file to read from.
     * @param clazz The type of the objects in the file.
     */
    public CSVObjectsFile(File file, Class clazz) {
        super(file);
        this.clazz = clazz;
    }

    /**
     * Creates a new CSVObjectsFile object.
     * @deprecated Use CSVObjectsFile(Path) instead.
     * @param path The string to generate the path to the file from.
     * @see Path
     * @param clazz The type of objects in the file.
     */
    @Deprecated
    public CSVObjectsFile(String path, Class clazz) {
        super(path);
        this.clazz = clazz;
    }

    /**
     * Creates a new CSVObjectsFile object.
     * @param path The path to the CSV file.
     * @see Path
     * @param clazz The type of objects in the file.
     */
    public CSVObjectsFile(Path path, Class clazz) {
        super(path);
        this.clazz = clazz;
    }

    /**
     * Returns the objects generated from the lines.
     * @return The objects generated from the lines.
     */
    public List<T> getObjects() {
        List<T> objects = new ArrayList<>();
        List<String[]> lines = get();
        for (String[] attributes : lines) {
            Constructor<T> constructor = null;
            try {
                constructor = clazz.getDeclaredConstructor(String[].class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            System.out.println(constructor);
            System.out.println(attributes.length);
            String[][] varargs = {attributes};
            T object = null;
            try {
                object = constructor.newInstance(varargs);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            objects.add(object);
        }

        return objects;
    }

    /**
     * Writes the given objects to the CSV file.
     * @param objects The objects.
     */
    public void writeObjects(List<T> objects) {
        List<String[]> attributesList = new ArrayList<>();
        for (T object : objects) {
            attributesList.add(object.toCSV());
        }
        write(attributesList);
    }
}
