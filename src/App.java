import common.FileIO;
import components.*;
import components.Config;

public class App {
    private static boolean hasTodoFolderAndFile = false;

    // TODO:
    // add case for list only unchecked todos (+update CLI options)
    // add case for removing all checked tasks
    // todo (bash alias) -r x → "unable to..." prints twice

    // TODO:
    // ISSUE:
    // todo -u new-user ... works fine
    // todo -l ... shows content for default user
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
        FileIO.createFolderIfAbsent(Config.getFolder());
        FileIO.createFileIfAbsent(Config.getFile());
    }

}
