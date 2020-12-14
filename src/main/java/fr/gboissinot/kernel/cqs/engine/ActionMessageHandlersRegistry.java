package fr.gboissinot.kernel.cqs.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Gregory Boissinot
 */
public class ActionMessageHandlersRegistry {

    private static ActionMessageHandlersRegistry INSTANCE;
    private final Map<Class<? extends ActionMessage>, Class<? extends ActionMessageHandler>> _mapped_actions;
    private final ActionMessageHandlerResolver messageHandlerResolver;

    private ActionMessageHandlersRegistry(ActionMessageHandlerResolver messageHandlerResolver) {
        this.messageHandlerResolver = messageHandlerResolver;
        this._mapped_actions = new HashMap<>();
    }

    public synchronized static ActionMessageHandlersRegistry valueOf(ActionMessageHandlerResolver messageHandlerResolver) {
        if (INSTANCE == null) {
            INSTANCE = new ActionMessageHandlersRegistry(messageHandlerResolver);
        }
        return INSTANCE;
    }

    public void subscribeHandler(Class<? extends ActionMessageHandler> actionMessageHandlerClass) {
        Class<? extends ActionMessage> actionMessageClass = messageHandlerResolver.resolveActionMessageType(actionMessageHandlerClass);
        if (actionMessageClass != null) {
            this._mapped_actions.putIfAbsent(actionMessageClass, Objects.requireNonNull(actionMessageHandlerClass));
        }
    }

    @SuppressWarnings("unchecked")
    <R, M extends ActionMessage, H extends ActionMessageHandler<R, M>> H findByActionMessageType(Class<M> messageClass) {
        Class<? extends H> messageHandlerClass = (Class<? extends H>) getActionMessageHandlerClass(messageClass);
        return messageHandlerResolver.getActionMessageHandler(messageHandlerClass);
    }

    private Class<? extends ActionMessageHandler> getActionMessageHandlerClass(Class<? extends ActionMessage> actionMessageClass) {
        Class<? extends ActionMessageHandler> actionMessageHandlerClass = _mapped_actions.get(Objects.requireNonNull(actionMessageClass));
        if (actionMessageHandlerClass == null) {
            throw new RuntimeException(String.format("No action message class associated to %s message..", actionMessageClass));
        }
        return actionMessageHandlerClass;
    }
}
