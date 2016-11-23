package io.lavoisier.channel.api;

/**
 * A condition is a condition to be met for a reaction to be executed
 */
public interface Condition {
    /**
     * Check whether or not the conditions is met
     *
     * @param parameters the condition parameters
     * @return whether or not the condition is met
     */
    boolean isMet(ConditionCheckParameters parameters);
}
