import common.FileIO;
import components.*;
import components.Config;

public class App {
    private static boolean hasTodoFolderAndFile = false;

    public static void main(String[] args) {
        createFileAndFolderIfNeeded();

        // ↓↓↓ dummy data for simulating CLI input, to be removed
        /*args = new String[]{
                "-r", "2"
        };*/

        // end of dummy data

        Args.handle(args);

    }

    // 1st run of the app → creation of folder and file for the app
    private static void createFileAndFolderIfNeeded() {
        if (!hasTodoFolderAndFile) {
            createFolderAndFileIfNeeded();
            hasTodoFolderAndFile = true;
        }
    }

    private static void createFolderAndFileIfNeeded() {
        FileIO.createFolderIfAbsent(Config.FOLDER.path);
        FileIO.createFileIfAbsent(Config.FILE.path);
    }

}
