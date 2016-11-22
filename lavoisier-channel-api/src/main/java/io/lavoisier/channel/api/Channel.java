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

package io.lavoisier.channel.api;

import java.io.InputStream;
import java.util.Map;

/**
 * A channel is a collection of {@link Spark}s, {@link Condition}s,
 * {@link Fuel}s and {@link Action}s related to a particular service.
 */
public interface Channel {
    /**
     * @return the inputstream of the xml descriptor of this channel.
     */
    InputStream getDescriptor();

    /**
     * @return a map of all {@link Spark}s of this channel indexed by spark id (defined in channel xml descriptor)
     */
    Map<String, Spark> getSparks();

    /**
     * @return a map of all {@link Condition}s of this channel indexed by condition id (defined in channel xml descriptor)
     */
    Map<String, Condition> getConditions();

    /**
     * @return a map of all {@link Fuel}s of this channel indexed by fuel id (defined in channel xml descriptor)
     */
    Map<String, Fuel> getFuels();

    /**
     * @return a map of all {@link Action}s of this channel indexed by action id (defined in channel xml descriptor)
     */
    Map<String, Action> getActions();
}
