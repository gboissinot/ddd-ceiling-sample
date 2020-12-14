package fr.gboissinot.kernel.event;

import java.time.LocalDateTime;

/**
 * Event class
 * <p>
 * It can't be modified. An event is immutable.
 *
 * @author Gregory Boissinot
 */
public interface Event {

    /**
     * Returns the unique identifier valueOf the event
     *
     * @return the identifier value
     */
    String id();

    /**
     * Returns the event version
     * It is used as a schema event version. Useful for migration valueOf the event structure.
     *
     * @return the version value
     */
    long version();

    /**
     * Returns the occurred time when the event was generated
     *
     * @return the event timestamp value
     */
    LocalDateTime occurredOn();

    /**
     * Returns the event type such as 'AUDIT', 'METRICS', 'DOMAIN' and so on.
     *
     * @return the type valueOf event value
     */
    String type();

    /**
     * Returns the event name
     *
     * @return the name valueOf event
     */
    String name();
}
