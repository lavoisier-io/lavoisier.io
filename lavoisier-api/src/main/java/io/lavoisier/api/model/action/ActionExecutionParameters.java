package io.lavoisier.api.model.action;

import java.util.HashMap;
import java.util.Map;

/**
 * Parameters needed for an action execution.
 *
 * It contains :
 *
 * - The action's channel global activation parameters
 * - The action's channel user activation parameters
 * - The action's input
 */
public final class ActionExecutionParameters {

    private Map<String, String> globalActivationParameters = new HashMap<>();

    private Map<String, String> userActivationParameters = new HashMap<>();

    private Map<String, String> input = new HashMap<>();

    public Map<String, String> getGlobalActivationParameters() {
        return globalActivationParameters;
    }

    public Map<String, String> getUserActivationParameters() {
        return userActivationParameters;
    }

    public Map<String, String> getInput() {
        return input;
    }
}
