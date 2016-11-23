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

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.lavoisier.channel.api.Channel;
import io.lavoisier.osgi.listeners.SelfRegisteringServiceListener;

/**
 * A ChannelRegistry is a {@link SelfRegisteringServiceListener} listening to {@link Channel} (un)registration and
 * keeping track of available {@link Channel}s in the application.
 */
@Component
public class ChannelRegistry implements SelfRegisteringServiceListener {

    private static final Logger logger = LoggerFactory.getLogger(ChannelRegistry.class);

    private Map<String, ServiceReference<Channel>> channels = new ConcurrentHashMap<>();

    private BundleContext bundleContext;

    @Override
    public void start(BundleContext context) {
        this.bundleContext = context;
        // Registering this ServiceListener
        try {
            context.addServiceListener(this, "(objectClass=io.lavoisier.channel.api.Channel)");
        } catch (InvalidSyntaxException e) {
            logger.error("Error while registering channel service listener", e);
            throw new RuntimeException(e);
        }

        // Registering all existing Channel instances
        Collection<ServiceReference<Channel>> serviceReferences;
        try {
            serviceReferences = context.getServiceReferences(Channel.class, null);
        } catch (InvalidSyntaxException e) {
            // filter is null, no InvalidSyntaxException should be thrown
            throw new RuntimeException(e);
        }
        for (ServiceReference<Channel> serviceReference : serviceReferences) {
            channels.put((String) serviceReference.getProperty("id"), serviceReference);
        }
    }

    @Override
    public void serviceChanged(ServiceEvent event) {
        switch (event.getType()) {
        case ServiceEvent.REGISTERED:
        case ServiceEvent.MODIFIED:
            // New or modified service, registering it (overwriting the previous one if needed)
            ServiceReference<Channel> serviceReference = (ServiceReference<Channel>) event.getServiceReference();
            channels.put((String) serviceReference.getProperty("id"), serviceReference);
            break;
        case ServiceEvent.MODIFIED_ENDMATCH:
        case ServiceEvent.UNREGISTERING:
            // Removing unregistered services
            channels.remove(event.getServiceReference().getProperty("id"));
            break;
        }
    }

    public Channel getChannel(String id) {
        return bundleContext.getService(channels.get(id));
    }

    public boolean ungetChannel(String id) {
        return bundleContext.ungetService(channels.get(id));
    }

    public Collection<String> getAvailableChannels() {
        return channels.keySet();
    }

    /*
     * private io.lavoisier.core.channel.xml.Channel unmarshall(InputStream descriptor) { try { JAXBContext context =
     * JAXBContext.newInstance(io.lavoisier.core.channel.xml.Channel.class); Unmarshaller unmarshaller =
     * context.createUnmarshaller(); io.lavoisier.core.channel.xml.Channel testChannel =
     * (io.lavoisier.core.channel.xml.Channel) unmarshaller.unmarshal(descriptor);
     *
     * return testChannel; } catch (JAXBException e) { logger.warn("Error while reading descriptor", e); return null; }
     * }
     */
}
