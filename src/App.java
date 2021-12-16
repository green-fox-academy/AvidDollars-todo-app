import common.FileIO;
import components.*;
import components.Configuration;

public class App {
    // TODO:
    // switch cases to add:
    // - show all users
    // - list all unchecked tasks
    // - remove all checked tasks
    // - check all todos
    // unit testing

    public static void main(String[] args) {
        createFolderAndFileIfNeeded();
        ArgumentHandler.handle(args);
    }

    private static void createFolderAndFileIfNeeded() {
        Configuration.initializeDefaultUser();
        FileIO.createFolderIfAbsent(Configuration.getTodoFolder());
        FileIO.createFileIfAbsent(Configuration.getFileForCurrentUser());
    }
}
