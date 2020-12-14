package fr.gboissinot.kernel.cqs.command.metrics;

import fr.gboissinot.kernel.cqs.command.CommandProcessor;
import fr.gboissinot.kernel.cqs.engine.ActionMessage;
import fr.gboissinot.kernel.cqs.engine.ActionMessageBusHandlerChain;
import fr.gboissinot.kernel.cqs.engine.metrics.LoggingExecutionTimeMetricsProcessor;

public class LoggingExecutionTimeMetricsCommandProcessor implements CommandProcessor {
    private final LoggingExecutionTimeMetricsProcessor timeMetricsActionMessageProcessor;

    public LoggingExecutionTimeMetricsCommandProcessor(LoggingExecutionTimeMetricsProcessor timeMetricsActionMessageProcessor) {
        this.timeMetricsActionMessageProcessor = timeMetricsActionMessageProcessor;
    }

    @Override
    public void process(ActionMessageBusHandlerChain chain, ActionMessage action) {
        timeMetricsActionMessageProcessor.process(chain, action);
    }
}
