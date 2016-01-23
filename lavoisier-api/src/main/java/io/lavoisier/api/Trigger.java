package io.lavoisier.api;

import io.lavoisier.api.model.trigger.TriggerExecutionParameters;
import io.lavoisier.api.model.trigger.TriggerExecutionResults;

/**
 * A trigger is an external event watched by a channel.
 */
public interface Trigger {
    TriggerExecutionResults execute(TriggerExecutionParameters params);
}
