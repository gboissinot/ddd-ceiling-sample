package fr.gboissinot.kernel.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <E>
 * @author Gregory Boissinot
 */
class SimpleEventDispatcher<E extends Event> implements EventDispatcher<E> {

    private static final Logger LOGGER = LogManager.getLogger(SimpleEventDispatcher.class);

    private final List<EventHandler<E>> handlers = new ArrayList<>();

    @Override
    public void dispatch(E event) {
        checkExistingHandlers(event);
        processHandlers(event);
    }

    private void processHandlers(E event) {
        LOGGER.debug("Processing {} event handlers", event.name());
        for (EventHandler<E> eventHandler : this.handlers) {
            eventHandler.handle(event);
        }
    }

    private void checkExistingHandlers(E event) {
        if (handlers.isEmpty()) {
            throw new IllegalStateException("No handler found for the event: " + event);
        }
    }

    @Override
    public void registerHandler(EventHandler<E> eventHandler) {
        LOGGER.debug("Registering {}", eventHandler.getClass().getSimpleName());
        handlers.add(eventHandler);
    }
}
