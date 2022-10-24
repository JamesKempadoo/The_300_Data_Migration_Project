package com.sparta.the300.loggers;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class CustomFileHandler {
    public static FileHandler getFileHandler() {
        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler("src/main/resources/sort-logger.log", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new CustomFormatting());
            return fileHandler;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
