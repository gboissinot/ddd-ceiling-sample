package fr.gboissinot.kernel.cqs.engine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Single-threaded dispatcher for retrieving the associated handler and invoke it
 *
 * @author Gregory Boissinot
 */
public class ActionMessageDispatcher {

    private static final Logger logger = LogManager.getLogger(ActionMessageDispatcher.class);

    private final ActionMessageHandlersRegistry handlersRegistry;

    public ActionMessageDispatcher(ActionMessageHandlersRegistry handlersRegistry) {
        this.handlersRegistry = handlersRegistry;
    }

    @SuppressWarnings("all")
    public <R, A extends ActionMessage, H extends ActionMessageHandler<R, A>> R dispatch(A action, List<? extends ActionMessageProcessor> processors) {

        //Get associated action handler by the action type
        H messageHandler = (H) handlersRegistry.findByActionMessageType(action.getClass());

        if (logger.isTraceEnabled()) {
            logger.trace("Dispatching {} to handler {}", action.name(), messageHandler.getClass().getSimpleName());
        }

        //Handle the action
        return (R) messageHandler.handle(action);
    }
}
