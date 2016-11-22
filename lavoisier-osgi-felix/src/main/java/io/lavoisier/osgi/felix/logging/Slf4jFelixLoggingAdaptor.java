package io.lavoisier.osgi.felix.logging;

import org.apache.felix.framework.Logger;
import org.osgi.framework.BundleException;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * The Slf4jOSGiLoggingAdaptor logs felix framework logs to Slf4j.
 */
@Component
public class Slf4jFelixLoggingAdaptor extends Logger {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Logger.class);

    @Override
    protected void doLogOut(int level, String s, Throwable throwable) {
        switch (level) {
        case LOG_DEBUG:
            LOGGER.debug(s);
            break;
        case LOG_ERROR:
            System.out.println("ERROR: " + s);
            if (throwable != null) {
                if ((throwable instanceof BundleException)
                        && (((BundleException) throwable).getNestedException() != null)) {
                    throwable = ((BundleException) throwable).getNestedException();
                }
                LOGGER.error(s, throwable);
            } else {
                LOGGER.error(s);
            }
            break;
        case LOG_INFO:
            LOGGER.info(s);
            break;
        case LOG_WARNING:
            LOGGER.warn(s);
            break;
        default:
            LOGGER.info("[" + level + "]: " + s);
        }
    }
}
