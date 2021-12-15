package enums;

// contains names for files and folders for the app
public enum Config {
    FOLDER("src/todo-files"),
    FILE("src/todo-files/todo-tasks");

    public final String path;

    private Config(String path) {
        this.path = path;
    }

}
