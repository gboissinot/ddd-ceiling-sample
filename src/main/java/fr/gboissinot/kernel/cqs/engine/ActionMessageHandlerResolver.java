package fr.gboissinot.kernel.cqs.engine;

/**
 * @author Gregory Boissinot
 */
public interface ActionMessageHandlerResolver {
    Class<? extends ActionMessage> resolveActionMessageType(Class<? extends ActionMessageHandler> actionMessageHandlerClass);

    <R, M extends ActionMessage, H extends ActionMessageHandler<R, M>> H getActionMessageHandler(Class<? extends H> messageHandlerClass);
}
