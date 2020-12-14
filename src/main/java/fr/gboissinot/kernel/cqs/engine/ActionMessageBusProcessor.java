package fr.gboissinot.kernel.cqs.engine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Spring {@link ActionMessage} bus implementation
 *
 * @author Gregory Boissinot
 */
public class ActionMessageBusProcessor {

    private static final Logger logger = LogManager.getLogger(ActionMessageBusProcessor.class);

    private final ActionMessageDispatcher dispatcher;
    private final List<? extends ActionMessageProcessor> processors;

    public ActionMessageBusProcessor(ActionMessageDispatcher dispatcher, List<? extends ActionMessageProcessor> processors) {
        this.dispatcher = dispatcher;
        this.processors = processors;
    }


    @SuppressWarnings("unchecked")
    public <R> R execute(ActionMessage action) {
        if (logger.isDebugEnabled()) {
            logger.debug("Executing {}.", action.name());
        }
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Dispatching {} to the matching handler.", action.name());
            }
            return (R) dispatcher.dispatch(action, processors);
        } catch (RuntimeException e) {
            if (logger.isErrorEnabled()) {
                logger.error(String.format("Can't handle action %s. Error : %s.", action.name(), e.getMessage()));
            }
            throw new ActionMessageBusException(e);
        }
    }
}

