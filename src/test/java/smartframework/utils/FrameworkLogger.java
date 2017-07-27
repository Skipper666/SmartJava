package smartframework.utils;

import org.apache.log4j.Logger;


public class FrameworkLogger {

    private static FrameworkLogger instance;
    public static final Logger LOG = Logger.getLogger(FrameworkLogger.class);

    private FrameworkLogger() {
    }

    public static synchronized FrameworkLogger getInstance() {
        if (instance == null) {
            instance = new FrameworkLogger();
        }
        return instance;
    }

    public void logStep(int num, String message) {
        LOG.info("=================================");
        LOG.info("=========Step " + num + ": " + message);
        LOG.info("=================================");
    }
}
