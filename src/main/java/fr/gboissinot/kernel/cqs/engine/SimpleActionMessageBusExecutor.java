package fr.gboissinot.kernel.cqs.engine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SimpleActionMessageBusExecutor<A extends ActionMessage> {

    private static final Logger logger = LogManager.getLogger(ActionMessageBusProcessor.class);

    private final ActionMessageDispatcher dispatcher;
    private final List<? extends ActionMessageProcessor> actionProcessors;

    public SimpleActionMessageBusExecutor(ActionMessageDispatcher dispatcher, List<ActionMessageProcessor> actionProcessors) {
        this.dispatcher = dispatcher;
        this.actionProcessors = actionProcessors;
    }

    @SuppressWarnings("unchecked")
    public <R> R execute(A action) {
        checkPreconditions(action);
        runProcessors(action, actionProcessors);
        return (R) dispatch(action);
    }

    private void checkPreconditions(A action) {
        if (logger.isDebugEnabled()) {
            logger.debug("Checking {} preconditions.", action.name());
        }
        action.check();
    }

    private void runProcessors(A action, List<? extends ActionMessageProcessor> actionProcessors) {
        if (logger.isDebugEnabled()) {
            logger.debug("Running {} processors.", action.name());
        }
        //Trigger the processors pipeline and the delegation to the handler through a MessageBusHandlerChain object
        ActionMessageBusHandlerChain chain = new DefaultMessageBusHandlerChain(action, actionProcessors);
        chain.next();
    }

    @SuppressWarnings("unchecked")
    private <R> R dispatch(ActionMessage action) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Dispatching {} to the matching handler.", action.name());
            }
            return (R) dispatcher.dispatch(action, actionProcessors);
        } catch (RuntimeException e) {
            if (logger.isErrorEnabled()) {
                logger.error(String.format("Can't dispatching %s. Error : %s.", action.name(), e.getMessage()));
            }
            throw new ActionMessageBusException(e);
        }
    }
}
