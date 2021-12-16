package components;

import java.util.Map;

import static common.OutToConsole.print;

public class Args {
    public static void handle(String[] args) {
        if (args.length == 0) {
            printUsage();
        }

        // try to make it more DRY
        if (args.length > 0) {
            switch (args[0]) {
                case "-h", "--help" -> {
                    printUsage();
                }

                case "-l", "--list" -> {
                    Cases.showTodos();
                }

                case "-a", "--add" -> {
                    if (args.length == 1) {
                        print("Unable to add: no task provided");
                        return;
                    }
                    Cases.addTodo(args);
                }

                case "-r", "--remove" -> {
                    if (args.length == 1) {
                        print("Unable to remove: no index provided");
                        return;
                    }
                    Cases.removeTodo(args);
                }

                case "-c", "--check" -> {
                    if (args.length == 1) {
                        print("Unable to check: no index provided");
                        return;
                    }
                    Cases.checkTodo(args);
                }

                case "-u", "--user" -> {
                    // changing users (=changing files)
                    if (args.length == 1) {
                        print("Unable to switch user: no name provided");
                        return;
                    }
                    Cases.switchUser(args);
                }

                default -> {
                    // TODO: error handling
                    print("Unsupported argument, type -h for help");
                }
            }
        }
    }

    // TODO: issue → easy to forget to update options if a new one is added
    // make implementation more dynamic → instead of switch stmt make use of HashMap
    private static void printUsage() {
        String header = "Command Line Todo application";
        String underline = "=".repeat(header.length());
        String optionsHeader = "Command line arguments:";

        Map<String, String> options = Map.of(
                "-a", "Adds a new task",
                "-c", "Completes an task",
                "-l", "Lists all the tasks",
                "-r", "Removes an task",
                "-u", "Changes user"
        );

        print(String.join("\n", header, underline, "", optionsHeader));

        for (Map.Entry<String, String> entry: options.entrySet()) {
            print(String.format("\t%s\t%s", entry.getKey(), entry.getValue()));
        }
    }
}
