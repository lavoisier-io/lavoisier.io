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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * The results of a {@link Spark}'s execution
 *
 * It consists of the output of the execution.
 *
 * The output is a list of spark outputs. It may be empty. Each output is a map of variable name / value.
 *
 * The cursor is a way for the spark to know where it stopped last time it was checked. The cursor is persisted by the
 * application, and passed to the next spark execution.
 *
 */
public final class SparkExecutionResults {

    private List<Map<String, Object>> output = new ArrayList<>();

    private Serializable cursor;

    public List<Map<String, Object>> getOutput() {
        return output;
    }

    public Serializable getCursor() {
        return cursor;
    }

    public void setCursor(Serializable cursor) {
        this.cursor = cursor;
    }

    public boolean addAll(Collection<? extends Map<String, Object>> c) {
        return output.addAll(c);
    }

    public boolean add(Map<String, Object> stringStringMap) {
        return output.add(stringStringMap);
    }

    public int size() {
        return output.size();
    }
}
