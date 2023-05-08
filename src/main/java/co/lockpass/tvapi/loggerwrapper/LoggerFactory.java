package co.lockpass.tvapi.loggerwrapper;

public class LoggerFactory {

    public static <T> Logger getLogger(Class<T> theClass) {
        return new Logger(theClass);
    }

}
