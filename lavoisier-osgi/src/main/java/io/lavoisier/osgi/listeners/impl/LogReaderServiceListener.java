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
package io.lavoisier.osgi.listeners.impl;

import io.lavoisier.osgi.listeners.SelfRegisteringServiceListener;
import io.lavoisier.osgi.logging.Slf4jLoggingAdaptor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.LinkedList;

@Component
public class LogReaderServiceListener implements SelfRegisteringServiceListener{
    private static final Logger logger = LoggerFactory.getLogger(ChannelRegistry.class);

    private BundleContext context;

    private LinkedList<LogReaderService> readers = new LinkedList<LogReaderService>();

    private Slf4jLoggingAdaptor adaptor;

    @Inject
    public LogReaderServiceListener(Slf4jLoggingAdaptor adaptor) {
        this.adaptor = adaptor;
    }

    @Override
    public void start(BundleContext bundleContext) {
        this.context = bundleContext;
        // Register this class as a listener to updates of the service list
        String filter = "(objectclass=" + LogReaderService.class.getName() + ")";
        try {
            context.addServiceListener(this, filter);
        } catch (InvalidSyntaxException e) {
            logger.error("Error while registering log reader service listener", e);
            throw new RuntimeException(e);
        }

        // Register the Slf4jLoggingAdaptor to all the LogReaderService objects available
        ServiceReference[] refs;
        try {
            refs = context.getServiceReferences(LogReaderService.class.getName(), null);
        } catch (InvalidSyntaxException e) {
            logger.error("Error while getting all references to log reader service", e);
            throw new RuntimeException(e);
        }
        if (refs != null) {
            for (ServiceReference ref : refs) {
                LogReaderService service = (LogReaderService) context.getService(ref);
                if (service != null) {
                    readers.add(service);
                    service.addLogListener(adaptor);
                }
            }
        }
    }

    @Override
    public void serviceChanged(ServiceEvent event) {
        LogReaderService lrs = (LogReaderService) context.getService(event.getServiceReference());
        if (lrs != null) {
            if (event.getType() == ServiceEvent.REGISTERED) {
                readers.add(lrs);
                lrs.addLogListener(adaptor);
            } else if (event.getType() == ServiceEvent.UNREGISTERING) {
                lrs.removeLogListener(adaptor);
                readers.remove(lrs);
            }
        }
    }
}
