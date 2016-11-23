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

import java.util.HashMap;
import java.util.Map;

/**
 * Parameters needed to get data from a {@link Fuel}
 *
 * Contains:
 * <ul>
 * <li>The fuel channel global activation parameters</li>
 * <li>The fuel channel user activation parameters</li>
 * <li>The fuel input</li>
 * </ul>
 */
public final class FuelDataParameters {

    private Map<String, Object> globalActivationParameters = new HashMap<>();

    private Map<String, Object> userActivationParameters = new HashMap<>();

    private Map<String, Object> input = new HashMap<>();

    public Map<String, Object> getGlobalActivationParameters() {
        return globalActivationParameters;
    }

    public Map<String, Object> getUserActivationParameters() {
        return userActivationParameters;
    }

    public Map<String, Object> getInput() {
        return input;
    }
}
