package fr.gboissinot.kernel.cqs.engine.metrics;

import fr.gboissinot.kernel.cqs.engine.ActionMessage;
import fr.gboissinot.kernel.cqs.engine.ActionMessageBusHandlerChain;
import fr.gboissinot.kernel.cqs.engine.ActionMessageProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingExecutionTimeMetricsProcessor implements ActionMessageProcessor {

    private static final Logger logger = LogManager.getLogger(LoggingExecutionTimeMetricsProcessor.class);

    @Override
    public void process(ActionMessageBusHandlerChain chain, ActionMessage action) {
        long start = System.currentTimeMillis();
        String actionName = action.name();
        try {
            logger.debug("Tracking execution time {}.", actionName);
            chain.next();
        } finally {
            long end = System.currentTimeMillis();
            long time = end - start;
            logger.debug("{} takes {} ms.", actionName, time);
        }
    }
}
