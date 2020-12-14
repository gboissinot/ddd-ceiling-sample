package fr.gboissinot.kernel.event;

/**
 * Represents a {@link Event} handler
 *
 * @param <E> the event
 * @author Gregory Boissinot
 */
public interface EventHandler<E extends Event> {
    /**
     * Handles the event from the {@link EventBus}
     *
     * @param event the event
     */
    void handle(E event);
}
