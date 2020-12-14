package fr.gboissinot.kernel.cqs.engine;

/**
 * @author Gregory Boissinot
 */
public interface ActionMessage extends ActionMessagePreconditions {
    default String name() {
        return this.getClass().getSimpleName();
    }
}

