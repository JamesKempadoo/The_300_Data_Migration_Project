package com.sparta.the300.loggers;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLoggerConfiguration {
    public static void getCustomLoggerConfiguration(Logger logger) {
        logger.setUseParentHandlers(false); //Don't use any logging from the root logger
        logger.addHandler(CustomFileHandler.getFileHandler());
        logger.addHandler(CustomConsoleHandler.getConsoleHandler());
        logger.setLevel(Level.ALL);
    }
}
