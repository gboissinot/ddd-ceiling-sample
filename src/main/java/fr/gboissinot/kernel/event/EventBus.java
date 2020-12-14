package fr.gboissinot.kernel.event;

/**
 * Event bus
 *
 * @param <E> the event
 * @author Gregory Boissinot
 */
public interface EventBus<E extends Event> {
    /**
     * Posts an event to the bus
     *
     * @param event the given event
     */
    void post(E event);

    /**
     * Registers an {@link Event} interceptor
     *
     * @param interceptor the event bus interceptor
     */
    void registerInterceptor(EventInterceptor<E> interceptor);

    /**
     * Registers an {@link Event} handler
     *
     * @param eventHandler the event bus handler
     */
    void registerHandler(EventHandler<E> eventHandler);
}
