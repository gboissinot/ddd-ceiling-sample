package fr.gboissinot.kernel.cqs.query;

import fr.gboissinot.kernel.cqs.engine.ActionMessageHandler;

/**
 * @param <R>
 * @param <Q>
 * @author Gregory Boissinot
 */
public interface QueryHandler<R, Q extends Query> extends ActionMessageHandler<R, Q> {
    R handle(Q query);
}
