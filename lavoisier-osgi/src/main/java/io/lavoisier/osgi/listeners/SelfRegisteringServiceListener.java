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
package io.lavoisier.osgi.listeners;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceListener;

/**
 * A SelfRegisteringServiceListener is a {@link ServiceListener} that knows how to register itself
 */
public interface SelfRegisteringServiceListener extends ServiceListener {
    /**
     * Register this {@link ServiceListener} into the {@link BundleContext}.
     *
     * @param bundleContext to register in
     */
    void start(BundleContext bundleContext);
}
