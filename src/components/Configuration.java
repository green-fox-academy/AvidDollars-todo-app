package components;

// contains names for files and folders for the app
public enum Configuration {
    FOLDER("src/todo-files"),
    FILE("src/todo-files/todo-tasks");

    public final String path;

    private Configuration(String path) {
        this.path = path;
    }

}
