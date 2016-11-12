/*
 * Copyright (C) 2016 Lavoisier.io
 *
 * This file is part of the Lavoisier.io project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.lavoisier.core.osgi.utils;

import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogListener;
import org.osgi.service.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * The Slf4jLoggingAdaptor converts the LogEntry objects it receives into calls to the
 * slf4j loggers.
 */
public class Slf4jLoggingAdaptor implements LogListener {

    private Map<Long, Logger> loggers = new HashMap<Long, Logger>();

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
        Logger logger = loggers.get(log.getBundle().getBundleId());
        if (logger == null) {
            logger = LoggerFactory.getLogger(log.getBundle().getSymbolicName());
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
