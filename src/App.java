import components.*;

import static common.OutToConsole.*;

import java.util.*;

public class App {
    private static boolean hasTodoFolderAndFile = false;

    public static void main(String[] args) {
        createFileAndFolderIfNeeded();

        // ↓↓↓ dummy data for simulating CLI input, to be removed
        args = new String[]{
                "-l"
        };

        // end of dummy data

        HandleArgs.process(args);

    }

    // 1st run of the app → creation of folder and file for the app
    private static void createFileAndFolderIfNeeded() {
        if (!hasTodoFolderAndFile) {
            InitProcedure.start();
            hasTodoFolderAndFile = true;
        }
    }

}
