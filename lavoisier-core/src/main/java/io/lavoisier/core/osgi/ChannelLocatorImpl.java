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
import org.apache.felix.framework.Felix;
import org.apache.felix.framework.util.FelixConstants;
import org.osgi.framework.*;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.util.*;

@Component
public class ChannelLocatorImpl implements ChannelLocator {

    private static final Logger logger = LoggerFactory.getLogger(ChannelLocatorImpl.class);

    private static final String BUNDLE_DIRECTORY = "bundle";

    private static final String CHANNEL_BUNDLE_DIRECTORY = "channel";

    @Autowired
    private BundleActivator hostActivator;

    private Felix felix;

    private ServiceTracker channelTracker;

    @PostConstruct
    public void init() {
        logger.info("Initializing Felix...");
        Map<String, Object> config = new HashMap<>();
        config.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA, "org.osgi.service.log;version=1.3.0," + Channel.class.getPackage().getName());
        config.put(Constants.FRAMEWORK_STORAGE_CLEAN, "onFirstInit");
        config.put(FelixConstants.LOG_LEVEL_PROP, "3");
        config.put(FelixConstants.SYSTEMBUNDLE_ACTIVATORS_PROP, Arrays.asList(hostActivator));

        felix = new Felix(config);

        try {
            felix.start();
        } catch (BundleException e) {
            logger.error("Error while initializing Felix", e);
            throw new RuntimeException(e);
        }

        startBundleInDirectory(BUNDLE_DIRECTORY, felix.getBundleContext());
        startBundleInDirectory(CHANNEL_BUNDLE_DIRECTORY, felix.getBundleContext());


        channelTracker = new ServiceTracker(felix.getBundleContext(), Channel.class.getName(), null);
        channelTracker.open();

        for(Object service : channelTracker.getServices()) {
            ((Channel) service).getDescriptor();
        }
    }

    @PreDestroy
    public void destroy() throws BundleException, InterruptedException {
        logger.info("Stopping Felix...");
        felix.stop();
        felix.waitForStop(1000L);
    }

    private void startBundleInDirectory(String directory, BundleContext context) {
        File bundleFolder = new File(directory);
        File[] bundleFilesList = bundleFolder.listFiles((dir, name) -> name.endsWith(".jar"));

        List<Bundle> installedBundles = new ArrayList<>();
        logger.info("Installing {} bundles in {}/.", bundleFilesList.length, directory);
        for(File bundleFile : bundleFilesList) {
            logger.info("Installing {}", bundleFile.getName());
            try {
                installedBundles.add(context.installBundle("file:" + directory + "/" + bundleFile.getName()));
            } catch (BundleException e) {
                logger.error("Error while installing bundle {}/{}", directory, bundleFile.getName(), e);
            }
        }

        for(Bundle bundle : installedBundles) {
            try {
                bundle.start();
            } catch (BundleException e) {
                logger.error("Error while starting bundle {}/{}", directory, bundle.getSymbolicName(), e);
            }
        }
    }
}
