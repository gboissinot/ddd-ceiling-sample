package fr.gboissinot.kernel.event;

/**
 * Represents a {@link EventBus} interceptor
 *
 * @param <E> the event
 * @author Gregory Boissinot
 */
public interface EventInterceptor<E extends Event> {
    /**
     * Intercepts a event from the {@link EventBus}
     *
     * @param event the event
     */
    void intercept(E event);
}
