package io.lavoisier.channel.api;

/**
 * A condition is a condition to be met for a reaction to be executed
 */
public interface Condition {
    boolean isMet(ConditionCheckParameters parameters);
}
