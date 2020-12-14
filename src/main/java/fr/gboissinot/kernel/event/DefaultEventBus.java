package fr.gboissinot.kernel.event;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Default Event Bus
 * The event processing is synchronous.
 *
 * @param <E> the event
 * @author Gregory Boissinot
 */
public class DefaultEventBus<E extends Event> implements EventBus<E> {

    private static final Logger logger = LogManager.getLogger(DefaultEventBus.class);

    private final List<EventInterceptor<E>> processors;
    private final SimpleEventDispatcher<E> dispatcher;

    public DefaultEventBus() {
        this.processors = new ArrayList<>();
        this.dispatcher = new SimpleEventDispatcher<>();
    }

    @Override
    public void post(E event) {
        E e = Objects.requireNonNull(event);
        logger.debug("Posting {}", e.name());
        for (EventInterceptor<E> interceptor : this.processors) {
            interceptor.intercept(e);
        }
        this.dispatcher.dispatch(e);
    }

    @Override
    public void registerInterceptor(EventInterceptor<E> eventInterceptor) {
        EventInterceptor<E> interceptor = Objects.requireNonNull(eventInterceptor);
        logger.debug("Registering interceptor {}", interceptor.getClass().getSimpleName());
        this.processors.add(interceptor);
    }

    @Override
    public void registerHandler(EventHandler<E> eventHandler) {
        EventHandler<E> handler = Objects.requireNonNull(eventHandler);
        logger.debug("Registering handler {}", handler.getClass().getSimpleName());
        this.dispatcher.registerHandler(handler);
    }
}
