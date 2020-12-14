package fr.gboissinot.kernel.cqs.engine;

/**
 * @author Gregory Boissinot
 */
public interface ActionMessageHandler<R, M extends ActionMessage> {
    R handle(M message);
}