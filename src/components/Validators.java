package components;

import common.FileIO;

import static common.OutToConsole.print;

public class Validators {

    // returns -1 invalid input
    protected static int validateInput(String[] args) {
        long linesCount = FileIO.countLines(Config.FILE.path);

        if (linesCount == 0) {
            print("file is empty");
            return -1;
        }

        if (!validInteger(args)) {
            print("Unable to check: index is not a number");
            return -1;
        }

        int todoToHandleIndex = Integer.parseInt(args[1]) - 1;

        if (todoToHandleIndex < 0 || todoToHandleIndex >= linesCount) {
            print("Unable to reach: index is out of bounds");
            return -1;
        }

        return todoToHandleIndex;
    }

    protected static boolean validInteger(String[] args) {
        try {
            Integer.parseInt(args[1]);
            return true;
        } catch (NumberFormatException e) {
            print("Unable to remove: index is not a number");
            return false;
        }
    }
}
