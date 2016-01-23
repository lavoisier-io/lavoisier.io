package io.lavoisier.api.model.trigger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * The results of a trigger's execution
 *
 * It consists of the output of the execution. The output is a list of event output. It may be empty.
 * Each event output is a map of variable name / value.
 *
 * The cursor is a way for the trigger to know where it stopped last time it was checked. The cursor is persisted
 * by the application, and passed to the next trigger's execution.
 *
 */
public final class TriggerExecutionResults {

    private List<Map<String, String>> output = new ArrayList<>();

    private Serializable cursor;

    public List<Map<String, String>> getOutput() {
        return output;
    }

    public Serializable getCursor() {
        return cursor;
    }

    public void setCursor(Serializable cursor) {
        this.cursor = cursor;
    }

    public boolean addAll(Collection<? extends Map<String, String>> c) {
        return output.addAll(c);
    }

    public boolean add(Map<String, String> stringStringMap) {
        return output.add(stringStringMap);
    }

    public int size() {
        return output.size();
    }
}
