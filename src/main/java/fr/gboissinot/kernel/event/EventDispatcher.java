package fr.gboissinot.kernel.event;

/**
 * @param <E>
 * @author Gregory Boissinot
 */
public interface EventDispatcher<E extends Event> {
    void dispatch(E event);

    void registerHandler(EventHandler<E> eventHandler);
}
