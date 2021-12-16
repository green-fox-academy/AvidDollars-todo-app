import common.FileIO;
import components.*;
import components.Configuration;

public class App {
    private static boolean hasTodoFolderAndFile = false;

    public static void main(String[] args) {
        createFileAndFolderIfNeeded();

        // ↓↓↓ dummy data for simulating CLI input, to be removed
        /*args = new String[]{
                "-r", "2"
        };*/

        // end of dummy data

        ArgumentHandler.handle(args);

    }

    // 1st run of the app → creation of folder and file for the app
    private static void createFileAndFolderIfNeeded() {
        if (!hasTodoFolderAndFile) {
            createFolderAndFileIfNeeded();
            hasTodoFolderAndFile = true;
        }
    }

    private static void createFolderAndFileIfNeeded() {
        FileIO.createFolderIfAbsent(Configuration.FOLDER.path);
        FileIO.createFileIfAbsent(Configuration.FILE.path);
    }

}
