package common;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

import static common.OutToConsole.printf;

//options for encoding
//import static java.nio.charset.StandardCharsets.*;

public class FileIO {
    public static List<String> readAllLines(String fileName) {
        try {
            Path filePath = Paths.get(fileName);
            return Files.readAllLines(filePath);

        } catch (IOException e) {
            printf("Unable to read file: %s\n", fileName);
            return new ArrayList<>();
        }
    }

    public static boolean fileExists(String fileName) {
        Path filePath = Paths.get(fileName);
        return Files.exists(filePath);
    }

    public static String readFirstLine(String fileName) {
        try {
            Path filePath = Paths.get(fileName);
            return Files.lines(filePath).findFirst().get();

        } catch (IOException e) {
            printf("Unable to read file: %s\n", fileName);
            return null;
        }
    }

    public static boolean deleteFile(String filename) {
        return new File(filename).delete();
    }

    public static boolean createFileIfAbsent(String filename) {
        try {
            new File(filename).createNewFile();
            return true;

        } catch (IOException e) {
            printf("Unable to create file: %s\n", filename);
            return false;
        }
    }

    public static boolean createFolderIfAbsent(String folderName) {
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdir();
            return false;
        }

        // if folder exists
        return true;
    }

    public static long countLines(String fileName) {
        try {
            Path filePath = Paths.get(fileName);
            Stream<String> stream = Files.lines(filePath);
            long res = stream.count();

            stream.close();
            return res;

        } catch (IOException e) {
            printf("Unable to read file: %s\n", fileName);
            return 0;
        }
    }

    public static boolean writeToFile(List<String> list, String fileName) {
        return toFile(list, fileName, StandardOpenOption.WRITE);
    }

    public static boolean appendToFile(List<String> list, String fileName) {
        return toFile(list, fileName, StandardOpenOption.APPEND);
    }

    public static boolean appendToFile(String str, String filename) {
        List<String> list = new ArrayList<>();
        list.add(str);
        return appendToFile(list, filename);
    }

    public static boolean writeToFile(String str, String filename) {
        List<String> list = new ArrayList<>();
        list.add(str);
        return writeToFile(list, filename);
    }

    private static boolean toFile(List<String> list, String fileName, StandardOpenOption option) {
        try {
            Files.write(Paths.get(fileName), list, option);
            return true;

        } catch (IOException e) {
            printf("Unable to write file: %s\n", fileName);
            return false;
        }
    }
}
