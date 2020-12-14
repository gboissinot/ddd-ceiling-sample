package fr.gboissinot.kernel.event.serializer;

import fr.gboissinot.kernel.event.Event;

/**
 * Event Serializer class providing a serializing form valueOf an {@link Event}
 *
 * @author Gregory Boissinot
 */
@FunctionalInterface
public interface EventSerializer<E extends Event> {
    /**
     * Format a {@link Event}
     *
     * @param event the specified event
     * @return the formatted event
     */
    String format(E event);
}
