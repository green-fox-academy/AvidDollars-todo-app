package components;

import common.FileIO;
import static common.OutToConsole.*;

public class Configuration {
    private static final String DEFAULT_USER = "default";
    private static final String TODO_FOLDER = "src/.todo-files";
    private static final String ACTUAL_USER_FILE = ".actual-user";

    private static void createFileAndSetUser(String userName) {
        FileIO.createFileIfAbsent(ACTUAL_USER_FILE);
        FileIO.writeToFile(userName, ACTUAL_USER_FILE);
    }

    private static String getActualUser() {
        return FileIO.readFirstLine(ACTUAL_USER_FILE);
    }

    public static String getTodoFolder() {
        return TODO_FOLDER;
    }

    public static void setCurrentUser(String userName) {
        //FileIO.deleteFile(ACTUAL_USER_FILE);
        createFileAndSetUser(userName);
    }

    public static String getFileForCurrentUser() {
        String actualUser = getActualUser();
        return String.format("%s/%s", TODO_FOLDER, actualUser);
    }

    public static void initializeDefaultUser() {
        if (!FileIO.fileExists(ACTUAL_USER_FILE)) {
            createFileAndSetUser(DEFAULT_USER);
        }
    }
}
