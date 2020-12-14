package fr.gboissinot.kernel.cqs.query.metrics;

import fr.gboissinot.kernel.cqs.engine.ActionMessage;
import fr.gboissinot.kernel.cqs.engine.ActionMessageBusHandlerChain;
import fr.gboissinot.kernel.cqs.engine.metrics.LoggingExecutionTimeMetricsProcessor;
import fr.gboissinot.kernel.cqs.query.QueryProcessor;

public class LoggingExecutionTimeMetricsQueryProcessor implements QueryProcessor {

    private final LoggingExecutionTimeMetricsProcessor timeMetricsActionMessageProcessor;

    public LoggingExecutionTimeMetricsQueryProcessor(LoggingExecutionTimeMetricsProcessor timeMetricsActionMessageProcessor) {
        this.timeMetricsActionMessageProcessor = timeMetricsActionMessageProcessor;
    }

    @Override
    public void process(ActionMessageBusHandlerChain chain, ActionMessage action) {
        timeMetricsActionMessageProcessor.process(chain, action);
    }
}
