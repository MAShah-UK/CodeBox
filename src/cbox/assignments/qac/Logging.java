package cbox.assignments.qac;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.*;

import static cbox.assignments.qac.Helper.currDate;

// Logs console output to file for historical/debugging purposes.
class Logging {
    private final String dir = "data\\logs\\";
    private final String fileName;
    private final Path filePath;

    private static final Logging inst = new Logging();

    private Logging() {
        final String dt = currDate("yyyy-MM-dd HH-mm-ss");
        fileName = "QA Cinemas (" + dt + ").txt";
        filePath = Paths.get(dir+fileName);
        try {
            Files.createDirectories(Paths.get(dir));
            if(!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch(IOException e) {
            print("Could not create directories/file: " + e.getMessage());
        }
    }

    public static Logging get() {
        return inst;
    }

    public void print(String str) {
        try {
            str += "\r\n";
            Files.write(filePath, str.getBytes(), StandardOpenOption.APPEND);
        } catch(IOException e) {
            print("Couldn't write to file: " + e.getMessage());
        }
    }
}
