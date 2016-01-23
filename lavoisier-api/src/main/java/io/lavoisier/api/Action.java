package io.lavoisier.api;

import io.lavoisier.api.model.action.ActionExecutionParameters;
import io.lavoisier.api.model.action.ActionExecutionResult;

/**
 * An action is something done in reaction to a trigger.
 */
public interface Action {

    ActionExecutionResult execute(ActionExecutionParameters params);
}
