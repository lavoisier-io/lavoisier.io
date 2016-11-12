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
package io.lavoisier.osgi.felix;

import io.lavoisier.api.Channel;
import io.lavoisier.osgi.HostActivator;
import org.apache.felix.fileinstall.internal.DirectoryWatcher;
import org.apache.felix.framework.Felix;
import org.apache.felix.framework.util.FelixConstants;
import org.osgi.framework.Constants;
import org.osgi.framework.launch.Framework;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Profile("felix")
public class FelixFrameworkConfiguration {

    private static final String CHANNEL_BUNDLE_DIRECTORY = "channel/";

    @Inject
    private HostActivator hostActivator;

    @Bean
    public Framework felix() {
        return new Felix(config(hostActivator));
    }

    private static Map<String, Object> config(HostActivator hostActivator) {
        Map<String, Object> config = new HashMap<>();
        config.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA, "org.osgi.service.log;version=1.3.0," + Channel.class.getPackage().getName());
        config.put(Constants.FRAMEWORK_STORAGE_CLEAN, "onFirstInit");
        config.put(FelixConstants.LOG_LEVEL_PROP, "3");
        config.put(FelixConstants.SYSTEMBUNDLE_ACTIVATORS_PROP, Arrays.asList(hostActivator));
        config.put(DirectoryWatcher.DIR, CHANNEL_BUNDLE_DIRECTORY);
        return config;
    }
}
