package fr.gboissinot.kernel.cqs.command;

import fr.gboissinot.kernel.cqs.engine.ActionPreconditionsFailureException;

/**
 * @author Gregory Boissinot
 */
public class CommandPreconditionsFailureException extends ActionPreconditionsFailureException {
    public CommandPreconditionsFailureException() {
    }

    public CommandPreconditionsFailureException(String message) {
        super(message);
    }

    public CommandPreconditionsFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandPreconditionsFailureException(Throwable cause) {
        super(cause);
    }

    public CommandPreconditionsFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
