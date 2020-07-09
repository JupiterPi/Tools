package jupiterpi.tools.files;

public class Test {
    public static void main(String[] args) {
        TextFile file = new TextFile(Path.getRunningDirectory().file("testfile.txt"));
        System.out.println(file.getFileForOutput());
        file.autosave();
        file.addLine("new line");
        //file.saveFile();
        file.addLine("laskdfjöaldjs");
    }
}