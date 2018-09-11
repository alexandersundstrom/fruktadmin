package com.evry.client.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
    private static final Logger logger = Logger.getLogger("FruktAdminEntryPoint");

    public static void info(String text) {
        logger.log(Level.INFO, text);
    }

    public static void error(String text) {
        logger.log(Level.SEVERE, text);
    }

    public static void warn(String text) {
        logger.log(Level.WARNING, text);
    }
}
