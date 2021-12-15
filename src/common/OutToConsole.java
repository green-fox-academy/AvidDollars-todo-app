package common;

// import it with static keyword:
// eg.: import static consoleout.OutToConsole.*;

public class OutToConsole {
    public static void print(Object obj) {
        System.out.println(obj);
    }

    public static void printf(String format, Object... objects) {
        System.out.printf(format, objects);
    }
}
