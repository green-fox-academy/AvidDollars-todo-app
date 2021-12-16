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

        return FileIO.appendToFile(toAdd, Configuration.FILE.path);
    };

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

            FileIO.appendToFile(lines.get(i), Configuration.FILE.path);
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
                FileIO.appendToFile(lines.get(i), Configuration.FILE.path);
            }
        }
    }

    // quite ineffective solution, but it works fine at a scale of simple CLI todo application
    // deletes old file, creates new blank file with the name of deleted one and returns the content of old file
    private static List<String> ClearFileAndReturnOldContent() {
        List<String> lines = FileIO.readAllLines(Configuration.FILE.path);
        FileIO.deleteFile(Configuration.FILE.path);
        FileIO.createFileIfAbsent(Configuration.FILE.path);
        return lines;
    }

    public static void showTodos() {
        long linesCount = FileIO.countLines(Configuration.FILE.path);

        if (linesCount == 0) {
            print("No todos for today! :)");
            return;
        }

        List<String> tasks = FileIO.readAllLines(Configuration.FILE.path);

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
