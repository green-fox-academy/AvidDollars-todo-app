import common.FileIO;
import components.*;
import components.Configuration;

public class App {
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
