package components;

import common.FileIO;
import static common.OutToConsole.print;

import java.util.List;

public class Cases {
    public static boolean addTodo(String[] args) {
        String toAdd = "";

        if (args.length == 2) {
            // "0,%s"... 0 = false (task is not completed)
            toAdd = String.format("0,%s", args[1]);
        }

        // handling multiple inputs for -a (--add)
        if (args.length > 2) {
            StringBuilder toAddStringBuilder = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                toAddStringBuilder.append(args[i]);
                toAddStringBuilder.append(" ");
            }
            // "0, "... 0 = false (task is not completed)
            toAdd = "0," + toAddStringBuilder;

        }

        return FileIO.appendToFile(toAdd, Configuration.getFileForCurrentUser());
    };

    public static void switchUser(String[] args) {
        String userName = args[1];
        Configuration.setCurrentUser(userName);
    }

    public static void checkTodo(String[] args) {
        int todoToHandleIndex = Validators.validateInput(args);

        // provided input was not valid
        if (todoToHandleIndex == -1) {
            return;
        }

        // returns a list of todos to process
        List<String> lines = ClearFileAndReturnOldContent();

        for (int i = 0; i < lines.size(); i++) {
            if (i == todoToHandleIndex) {
                String todo = lines.get(i);

                // .split(regex, limit: 2) → just one split is done
                String[] todoParts = todo.split(",", 2);

                // "1," ... 1 = true (task is completed)
                String checkedTodo = "1," + todoParts[1];
                lines.set(i, checkedTodo);
            }

            FileIO.appendToFile(lines.get(i), Configuration.getFileForCurrentUser());
        }
    }

    public static void removeTodo(String[] args) {
        int todoToHandleIndex = Validators.validateInput(args);

        // provided input was not valid
        if (todoToHandleIndex == -1) {
            return;
        }

        List<String> lines = ClearFileAndReturnOldContent();

        for (int i = 0; i < lines.size(); i++) {
            if (i != todoToHandleIndex) {
                FileIO.appendToFile(lines.get(i), Configuration.getFileForCurrentUser());
            }
        }
    }

    // quite ineffective solution, but it works fine at a scale of simple CLI todo application
    // deletes old file, creates new blank file with the name of deleted one and returns the content of old file
    private static List<String> ClearFileAndReturnOldContent() {
        List<String> lines = FileIO.readAllLines(Configuration.getFileForCurrentUser());
        FileIO.deleteFile(Configuration.getFileForCurrentUser());
        FileIO.createFileIfAbsent(Configuration.getFileForCurrentUser());
        return lines;
    }

    public static void showTodos() {
        long linesCount = FileIO.countLines(Configuration.getFileForCurrentUser());
        String userHeader = "user: " + Configuration.getActualUser();
        String userHeaderUnderline = "=".repeat(userHeader.length());
        print(String.format("%s\n%s", userHeader, userHeaderUnderline));

        if (linesCount == 0) {
            print("No todos for today! :)");
            return;
        }

        List<String> tasks = FileIO.readAllLines(Configuration.getFileForCurrentUser());

        int index = 1;

        for (String task: tasks) {
            printFormattedTodo(task, index);
            index++;
        }
    }

    private static void printFormattedTodo(String task, int index) {
        // limit: 2 → just one split will be done
        String[] strParts = task.split(",", 2);
        String ifDone = (strParts[0].equals("1")) ? "[x]" : "[ ]";
        print(String.format("%d - %s %s", index, ifDone, strParts[1]));
    }
}
