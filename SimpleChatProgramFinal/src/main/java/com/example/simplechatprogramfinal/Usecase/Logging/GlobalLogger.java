package com.example.simplechatprogramfinal.Usecase.Logging;

import java.util.logging.Logger;

public class GlobalLogger {
    private static final Logger logger = Logger.getLogger(GlobalLogger.class.getName());

    public static void logError(String message, Exception e) {
        logger.severe(message + ": " + e.getMessage());
    }

    public static void logInfo(String message) {
        logger.info(message);
    }
}
