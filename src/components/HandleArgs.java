package components;

import java.util.Map;

import static common.OutToConsole.print;

public class HandleArgs {
    public static void process(String[] args) {
        if (args.length == 0) {
            printUsage();
        }

        if (args.length > 0) {
            switch (args[0]) {
                case "-l", "--list" -> {
                    Cases.showTodos();
                }

                case "-a", "--add" -> {
                    // TODO: error handling if just -a and nothing else
                    Cases.addTodo(args);
                }

                case "-r", "--remove" -> {
                    // TODO: error handling
                    Cases.removeTodo(args);
                }

                case "-c", "--check" -> {
                    // TODO: error handling
                    Cases.checkTodo(args);
                }

                default -> {
                    // TODO: error handling
                    print("default case invoked");
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
