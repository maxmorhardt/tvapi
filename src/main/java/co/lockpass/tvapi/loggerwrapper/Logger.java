package co.lockpass.tvapi.loggerwrapper;

import org.slf4j.LoggerFactory;

/**
 * slf4j logger with colors
 */
public class Logger {

    private final org.slf4j.Logger logger;

    private final String GREEN = "\u001b[32m";
    private final String RED = "\u001b[31m";
    private final String BLUE = "\u001b[34m";
    private final String RESET = "\u001b[0m";
    private final String ORANGE ="\u001b[33m";

    public <T> Logger(Class<T> theClass) {
        logger = LoggerFactory.getLogger(theClass);
    }

    public void info(String s) {
        logger.info(s);
    }

    public void success(String s) {
        logger.info(GREEN + s + RESET);
    }

    public void start(String s) {
        logger.info(BLUE + s + RESET);
    }

    public void warn(String s) {
        logger.warn(ORANGE + s + RESET);
    }

    public void error(String s) {
        logger.error(RED + s + RESET);
    }

    public void error(String s, Exception e) {
        logger.error(RED + s + RESET, e);
    }

}
