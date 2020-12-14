package fr.gboissinot.kernel.cqs.engine;

/**
 * @author Gregory Boissinot
 */
public class ActionMessageHandlerNotFoundException extends ActionMessageBusException {
    public ActionMessageHandlerNotFoundException(Class<? extends ActionMessageHandler> messageHandlerClass) {
        super("Message bus handler not found : " + messageHandlerClass);
    }
}
