package fr.gboissinot.kernel.cqs.query;

import fr.gboissinot.kernel.cqs.engine.ActionMessage;

/**
 * @author Gregory Boissinot
 */
public interface Query extends ActionMessage {

    @Override
    default void check() throws QueryPreconditionsFailureException {
    }
}
