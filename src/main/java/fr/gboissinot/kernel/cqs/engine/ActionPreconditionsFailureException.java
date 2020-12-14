package fr.gboissinot.kernel.cqs.engine;

/**
 * @author Gregory Boissinot
 */
public class ActionPreconditionsFailureException extends RuntimeException {
    public ActionPreconditionsFailureException() {
    }

    public ActionPreconditionsFailureException(String message) {
        super(message);
    }

    public ActionPreconditionsFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionPreconditionsFailureException(Throwable cause) {
        super(cause);
    }

    public ActionPreconditionsFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
