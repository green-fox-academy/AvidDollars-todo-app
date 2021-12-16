package components;

public class Config {
    private static String FOLDER = "src/todo-files";
    private static String FILE = "src/todo-files/default";

    private static String setFullFilePath(String folder, String user) {
        return String.format("%s/%s", folder, user);
    }

    public static void setUser(String name) {
        Config.FILE = setFullFilePath(FOLDER, name);
    }

    public static String getFolder() {
        return Config.FOLDER;
    }

    public static String getFile() {
        return Config.FILE;
    }
}