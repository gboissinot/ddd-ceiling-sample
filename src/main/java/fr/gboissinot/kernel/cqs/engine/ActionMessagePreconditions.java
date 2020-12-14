package fr.gboissinot.kernel.cqs.engine;

/**
 * @author Gregory Boissinot
 */
public interface ActionMessagePreconditions {
    default void check() throws ActionPreconditionsFailureException {
    }
}
