package fr.gboissinot.kernel.cqs.query;

import fr.gboissinot.kernel.cqs.engine.ActionPreconditionsFailureException;

/**
 * @author Gregory Boissinot
 */
public class QueryPreconditionsFailureException extends ActionPreconditionsFailureException {
    public QueryPreconditionsFailureException() {
    }

    public QueryPreconditionsFailureException(String message) {
        super(message);
    }

    public QueryPreconditionsFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public QueryPreconditionsFailureException(Throwable cause) {
        super(cause);
    }

    public QueryPreconditionsFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
