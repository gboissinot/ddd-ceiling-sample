package fr.gboissinot.kernel.cqs.engine;

/**
 * @author Gregory Boissinot
 */
public interface ActionMessageProcessor {
    void process(ActionMessageBusHandlerChain chain, ActionMessage action);
}
