package components;

import java.util.Map;

import static common.OutToConsole.print;

public class Args {
    public static void handle(String[] args) {
        if (args.length == 0) {
            printUsage();
        }

        if (args.length > 0) {
            switch (args[0]) {
                case "-l", "--list" -> {
                    Cases.showTodos();
                }

                case "-a", "--add" -> {
                    if (args.length == 1) {
                        print("Unable to add: no index provided");
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
                    Cases.checkTodo(args);
                }

                default -> {
                    print("Unsupported argument");
                }
            }
        }
    }

    private static void printUsage() {
        String header = "Command Line Todo application";
        String underline = "=".repeat(header.length());
        String optionsHeader = "Command line arguments:";

        Map<String, String> options = Map.of(
                "-l", "Lists all the tasks",
                "-a", "Adds a new task",
                "-r", "Removes an task",
                "-c", "Completes an task"
        );

        print(String.join("\n", header, underline, "", optionsHeader));

        for (Map.Entry<String, String> entry: options.entrySet()) {
            print(String.format("\t%s\t%s", entry.getKey(), entry.getValue()));
        }
    }
}
