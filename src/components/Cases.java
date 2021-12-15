package components;

import common.FileIO;
import enums.*;
import static common.OutToConsole.print;

import java.util.List;

public class Cases {

    public static boolean addTodo(String[] args) {
        String toAdd = String.format("0,%s", args[1]);
        return FileIO.appendToFile(toAdd, Config.FILE.path);
    };

    public static void checkTodo(String[] args) {
        modifyTodoFile(args, Modification.CHECKING);
    }

    public static void removeTodo(String[] args) {

        modifyTodoFile(args, Modification.REMOVING);

        /*// not very optimal way
        List<String> lines = FileIO.readAllLines(Config.FILE.path);
        FileIO.deleteFile(Config.FILE.path);
        InitProcedure.start();

        // TODO: handle case when arg cannot be parsed to an int
        // TODO: clean string of possible empty spaces

        int toRemoveIdx = Integer.parseInt(args[1]) - 1;

        for (int i = 0; i < lines.size(); i++) {
            if (i != toRemoveIdx) {
                FileIO.appendToFile(lines.get(i), Config.FILE.path);
            }
        }*/
    }

    // TODO: to be refactored
    private static void modifyTodoFile(String[] args, Modification option) {
        // not very optimal way
        List<String> lines = FileIO.readAllLines(Config.FILE.path);
        FileIO.deleteFile(Config.FILE.path);
        InitProcedure.start();

        // TODO: handle case when arg cannot be parsed to an int
        // TODO: clean string of possible empty spaces

        int todoToHandle = Integer.parseInt(args[1]) - 1;

        for (int i = 0; i < lines.size(); i++) {
            if (option == Modification.REMOVING) {
                if (i != todoToHandle) {
                    FileIO.appendToFile(lines.get(i), Config.FILE.path);
                }
            }

            if (option == Modification.CHECKING) {
                if (i == todoToHandle) {
                    String todo = lines.get(i);
                    String[] todoParts = todo.split(",", 2);
                    String checktedTodo = "1," + todoParts[1];
                    lines.set(i, checktedTodo);

                }

                FileIO.appendToFile(lines.get(i), Config.FILE.path);
            }
        }
    }

    public static void showTodos() {
        long linesCount = FileIO.countLines(Config.FILE.path);

        if (linesCount == 0) {
            print("No todos for today! :)");
            return;
        }

        showList();
    }

    private static void showList() {
        List<String> tasks = FileIO.readAllLines(Config.FILE.path);

        int index = 1;

        for (String task: tasks) {
            formatForPrint(task, index);
            index++;
        }
    }

    private static void formatForPrint(String task, int index) {
        // limit: 2 → just one split will be done
        String[] strParts = task.split(",", 2);
        String ifDone = (strParts[0].equals("1")) ? "[x]" : "[ ]";
        print(String.format("%d - %s %s", index, ifDone, strParts[1]));
    }
}
