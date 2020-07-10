package jupiterpi.tools.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourcesFile extends TextFile {
    protected String name;

    public ResourcesFile(String name) {
        super(Path.getRunningDirectory().file("delete-this.txt"));
        this.name = name;
        InputStream is = getClass().getClassLoader().getResourceAsStream(name);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        read(br);
    }

    @Override
    protected void read(File file) {}

    @Override
    public void saveFile() {
        System.err.println("You are trying to edit a read-only resources file! (" + name + ")");
    }
}