package io.lavoisier.core.osgi.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogListener;
import org.osgi.service.log.LogService;

import java.util.HashMap;
import java.util.Map;

/**
 * The CommonsLoggingAdaptor converts the LogEntry objects it receives into calls to the
 * commons logging loggers.
 */
public class CommonsLoggingAdaptor implements LogListener {

    private Map<Long, Log> loggers = new HashMap<Long, Log>();

    /**
     * This methods is called by the LogReaderService, and dispatch them to
     * a set of Loggers, created with
     */
    @Override
    public void logged(LogEntry log) {
        if ((log.getBundle() == null) || (log.getBundle().getSymbolicName() == null)) {
            // if there is no name, it's probably the framework emitting a log
            // This should not happen and we don't want to log something anonymous
            return;
        }

        // Retrieve a Log object, or create it if none exists.
        Log logger = loggers.get(log.getBundle().getBundleId());
        if (logger == null) {
            logger = LogFactory.getLog(log.getBundle().getSymbolicName());
            loggers.put(log.getBundle().getBundleId(), logger);
        }

        // If there is an exception available, use it, otherwise just log
        // the message
        if (log.getException() != null) {
            switch (log.getLevel()) {
                case LogService.LOG_DEBUG:
                    logger.debug(log.getMessage(), log.getException());
                    break;
                case LogService.LOG_INFO:
                    logger.info(log.getMessage(), log.getException());
                    break;
                case LogService.LOG_WARNING:
                    logger.warn(log.getMessage(), log.getException());
                    break;
                case LogService.LOG_ERROR:
                    logger.error(log.getMessage(), log.getException());
                    break;
            }
        } else {
            switch (log.getLevel()) {
                case LogService.LOG_DEBUG:
                    logger.debug(log.getMessage());
                    break;
                case LogService.LOG_INFO:
                    logger.info(log.getMessage());
                    break;
                case LogService.LOG_WARNING:
                    logger.warn(log.getMessage());
                    break;
                case LogService.LOG_ERROR:
                    logger.error(log.getMessage());
                    break;
            }
        }
    }
}
