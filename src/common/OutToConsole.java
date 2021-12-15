package common;

// to be used with static import:
// eg.: import static common.OutToConsole.*;

public class OutToConsole {
    public static void print(Object obj) {
        System.out.println(obj);
    }

    public static void printf(String format, Object... objects) {
        System.out.printf(format, objects);
    }
}
