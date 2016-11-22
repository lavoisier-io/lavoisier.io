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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Parameters needed for a spark execution.
 *
 * It contains :
 *
 * - The spark channel global activation parameters
 * - The spark channel user activation parameters
 * - The spark input
 * - The cursor returned by last spark execution. null is passed for the first execution of this spark.
 */
public final class SparkExecutionParameters {

    private Map<String, Object> globalActivationParameters = new HashMap<>();

    private Map<String, Object> userActivationParameters = new HashMap<>();

    private Map<String, Object> input = new HashMap<>();

    private Serializable lastCheckCursor;

    public Map<String, Object> getGlobalActivationParameters() {
        return globalActivationParameters;
    }

    public Map<String, Object> getUserActivationParameters() {
        return userActivationParameters;
    }

    public Map<String, Object> getInput() {
        return input;
    }

    public Serializable getLastCheckCursor() {
        return lastCheckCursor;
    }

    public void setLastCheckCursor(Serializable lastCheckCursor) {
        this.lastCheckCursor = lastCheckCursor;
    }
}
