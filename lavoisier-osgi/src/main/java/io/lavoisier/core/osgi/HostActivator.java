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

package io.lavoisier.core.osgi;

import io.lavoisier.core.osgi.utils.Slf4jLoggingAdaptor;
import org.osgi.framework.*;
import org.osgi.service.log.LogReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * The HostActivator registers various Services for availability in the osgi container.
 */
@Component
public class HostActivator implements BundleActivator, ServiceListener {

    private static final Logger logger = LoggerFactory.getLogger(HostActivator.class);

    private Slf4jLoggingAdaptor adaptor = new Slf4jLoggingAdaptor();

    private LinkedList<LogReaderService> readers = new LinkedList<LogReaderService>();

    private BundleContext context;

    @Override
    public void start(BundleContext context) throws Exception {
        this.context = context;
        // Register this class as a listener to updates of the service list
        String filter = "(objectclass=" + LogReaderService.class.getName() + ")";
        try {
            context.addServiceListener(this, filter);
        } catch (InvalidSyntaxException e) {
            assert false : "addServiceListener failed: " + e.getMessage();
        }

        // Register the Slf4jLoggingAdaptor to all the LogReaderService objects available
        // on the server.
        ServiceReference[] refs = context.getServiceReferences(LogReaderService.class.getName(), null);
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
    public void stop(BundleContext context) throws Exception {
        for (Iterator<LogReaderService> i = readers.iterator(); i.hasNext(); ) {
            LogReaderService lrs = i.next();
            lrs.removeLogListener(adaptor);
            i.remove();
        }
    }

    //  We use a ServiceListener to dynamically keep track of all the LogReaderService service being
    //  registered or unregistered
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

    public Bundle[] getBundles() {
        Bundle[] bundles = null;
        if (context != null) {
            bundles = context.getBundles();
        }
        return bundles;
    }
}
