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

import io.lavoisier.api.Channel;
import org.osgi.framework.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelListener implements ServiceListener {

    private static final Logger logger = LoggerFactory.getLogger(ChannelListener.class);

    private Map<String, io.lavoisier.core.channel.xml.Channel> channels = new ConcurrentHashMap<>();

    private BundleContext bundleContext;

    public void start(BundleContext context) {
        this.bundleContext = context;
        Collection<ServiceReference<Channel>> serviceReferences = Collections.emptyList();
        try {
            serviceReferences = context.getServiceReferences(Channel.class, null);
        } catch (InvalidSyntaxException e) {
            //filter is null, no SyntaxException should be thrown
        }
        for(ServiceReference<Channel> serviceReference : serviceReferences) {
            Channel service = context.getService(serviceReference);
            channels.put((String) serviceReference.getProperty("id"), unmarshall(service.getDescriptor()));
            context.ungetService(serviceReference);
        }
    }

    public Collection<io.lavoisier.core.channel.xml.Channel> getAllChannels() {
        return channels.values();
    }

    public io.lavoisier.core.channel.xml.Channel getChannel(String id) {
        return channels.get(id);
    }

    @Override
    public void serviceChanged(ServiceEvent event) {
        switch (event.getType()) {
            case ServiceEvent.REGISTERED:
            case ServiceEvent.MODIFIED:
            case ServiceEvent.MODIFIED_ENDMATCH:
                ServiceReference<Channel> serviceReference = (ServiceReference<Channel>) event.getServiceReference();
                Channel service = bundleContext.getService(serviceReference);
                channels.put((String) serviceReference.getProperty("id"), unmarshall(service.getDescriptor()));
                bundleContext.ungetService(serviceReference);
                break;
            case ServiceEvent.UNREGISTERING:
                channels.remove(event.getServiceReference().getProperty("id"));
                break;
        }
    }

    private io.lavoisier.core.channel.xml.Channel unmarshall(InputStream descriptor) {
        try {
            JAXBContext context = JAXBContext.newInstance(io.lavoisier.core.channel.xml.Channel.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            io.lavoisier.core.channel.xml.Channel testChannel = (io.lavoisier.core.channel.xml.Channel) unmarshaller.unmarshal(descriptor);

            return testChannel;
        } catch (JAXBException e) {
            logger.warn("Error while reading descriptor", e);
            return null;
        }
    }
}
