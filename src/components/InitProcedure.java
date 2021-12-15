package components;

import common.FileIO;
import enums.Config;

public class InitProcedure {
    public static void start() {
        FileIO.createFolderIfAbsent(Config.FOLDER.path);
        FileIO.createFileIfAbsent(Config.FILE.path);
    }
}
