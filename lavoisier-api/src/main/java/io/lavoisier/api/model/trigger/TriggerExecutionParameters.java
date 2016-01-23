package io.lavoisier.api.model.trigger;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Parameters needed for a trigger's execution.
 *
 * It contains :
 *
 * - The trigger's channel global activation parameters
 * - The trigger's channel user activation parameters
 * - The trigger's input
 * - The cursor returned by last trigger's execution. null is passed for the first execution of this trigger.
 */
public final class TriggerExecutionParameters {

    private Map<String, String> globalActivationParameters = new HashMap<>();

    private Map<String, String> userActivationParameters = new HashMap<>();

    private Map<String, String> input = new HashMap<>();

    private Serializable lastCheckCursor;

    public Map<String, String> getGlobalActivationParameters() {
        return globalActivationParameters;
    }

    public Map<String, String> getUserActivationParameters() {
        return userActivationParameters;
    }

    public Map<String, String> getInput() {
        return input;
    }

    public Serializable getLastCheckCursor() {
        return lastCheckCursor;
    }

    public void setLastCheckCursor(Serializable lastCheckCursor) {
        this.lastCheckCursor = lastCheckCursor;
    }
}
