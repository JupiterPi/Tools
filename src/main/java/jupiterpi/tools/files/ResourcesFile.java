package jupiterpi.tools.files;

import jupiterpi.tools.files.csv.CSVFile;
import jupiterpi.tools.files.json.JSONFile;

import java.io.File;

public class ResourcesFile {
    String name;

    public ResourcesFile(String name) {
        this.name = name;
    }

    public File file() {
        return new File(getClass().getClassLoader().getResource(name).getFile());
    }

    public TextFile textFile() {
        return new TextFile(file());
    }

    public CSVFile csvFile() {
        return new CSVFile(file());
    }

    public JSONFile jsonFile() {
        return new JSONFile(file());
    }
}