package fr.gboissinot.kernel.cqs.engine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Gregory Boissinot
 */
class DefaultMessageBusHandlerChain implements ActionMessageBusHandlerChain {

    private static final Logger LOGGER = LogManager.getLogger(DefaultMessageBusHandlerChain.class);

    private final ActionMessage message;
    private final List<? extends ActionMessageProcessor> processors;
    private final AtomicInteger cursor;

    DefaultMessageBusHandlerChain(ActionMessage message, List<? extends ActionMessageProcessor> processors) {
        this(message, processors, 0);
    }

    private DefaultMessageBusHandlerChain(ActionMessage message, List<? extends ActionMessageProcessor> processors, int cursor) {
        this.message = message;
        this.processors = processors;
        this.cursor = new AtomicInteger(cursor);
    }

    @Override
    public void next() {

        LOGGER.trace("Triggering next interceptor");

        if (isLastProcessor()) {
            return;
        }

        int processorCursor = cursor.get();
        incrementCursor();
        processNextProcessor(processorCursor);
    }


    @SuppressWarnings("unchecked")
    private void processNextProcessor(int interceptorPosition) {
        ActionMessageProcessor currentMessageInterceptor = processors.get(interceptorPosition);
        LOGGER.trace("Processing {}", currentMessageInterceptor.getClass().getSimpleName());
        currentMessageInterceptor.process(new DefaultMessageBusHandlerChain(message, getProcessors(), getCurrentCursor()), message);
    }

    private void incrementCursor() {
        this.cursor.incrementAndGet();
    }

    private boolean isLastProcessor() {
        return cursor.get() == processors.size();
    }

    private List<? extends ActionMessageProcessor> getProcessors() {
        return Collections.unmodifiableList(processors);
    }

    private int getCurrentCursor() {
        return cursor.get();
    }
}
