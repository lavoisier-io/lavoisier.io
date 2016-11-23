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
package io.lavoisier.osgi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.launch.Framework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.lavoisier.osgi.listeners.SelfRegisteringServiceListener;

/**
 * Basic OSGi configuration, to be kept OSGi-implementation independent.
 */
@Configuration
public class OSGiConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(OSGiConfiguration.class);

    @Inject
    private List<SelfRegisteringServiceListener> listeners;

    @Inject
    private Framework osgiFramework;

    @Inject
    private Environment env;

    @PostConstruct
    public void init() {
        logger.info("Initializing OSGi Framework...");
        try {
            osgiFramework.init();
        } catch (BundleException e) {
            logger.error("Error while initializing OSGi Framework", e);
            throw new RuntimeException(e);
        }

        logger.debug("Registering service listeners...");
        for (SelfRegisteringServiceListener listener : listeners) {
            listener.start(osgiFramework.getBundleContext());
        }

        logger.debug("Starting all system bundles...");
        startAllBundlesInDirectory(env.getProperty("lavoisier.osgi.system-bundles-directory"),
                osgiFramework.getBundleContext());

        logger.info("Starting OSGi Framework...");
        try {
            osgiFramework.start();
        } catch (BundleException e) {
            logger.error("Error while starting OSGi Framework", e);
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void destroy() throws BundleException, InterruptedException {
        logger.info("Stopping OSGi Framework...");
        osgiFramework.stop();
        osgiFramework.waitForStop(1000L);
    }

    private void startAllBundlesInDirectory(String directory, BundleContext context) {
        File bundleFolder = new File(directory);
        File[] bundleFilesList = bundleFolder.listFiles((dir, name) -> name.endsWith(".jar"));

        if (bundleFilesList == null) {
            bundleFilesList = new File[] {};
        }

        List<Bundle> installedBundles = new ArrayList<>();
        logger.info("Installing {} bundles in {}.", bundleFilesList.length, directory);
        for (File bundleFile : bundleFilesList) {
            logger.info("Installing {}", bundleFile.getName());
            try {
                installedBundles.add(context.installBundle("file:" + directory + bundleFile.getName()));
            } catch (BundleException e) {
                logger.error("Error while installing bundle {}{}", directory, bundleFile.getName(), e);
            }
        }

        for (Bundle bundle : installedBundles) {
            try {
                bundle.start();
            } catch (BundleException e) {
                logger.error("Error while starting bundle {}{}", directory, bundle.getSymbolicName(), e);
            }
        }
    }
}
