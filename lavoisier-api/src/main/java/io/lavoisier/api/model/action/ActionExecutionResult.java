package io.lavoisier.api.model.action;

/**
 * The result of an action's execution.
 *
 * It is just a boolean indicating whether or not it succeeds, and a message in case of failure
 */
public final class ActionExecutionResult {

    private boolean success;

    private String errorMessage;

    private ActionExecutionResult(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static ActionExecutionResult success() {
        return new ActionExecutionResult(true, null);
    }

    public static ActionExecutionResult failure(String message) {
        return new ActionExecutionResult(false, message);
    }
}
