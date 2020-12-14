package fr.gboissinot.kernel.ddd.event;

import fr.gboissinot.kernel.event.Event;
import fr.gboissinot.kernel.java.annotation.ddd.DDD;

/**
 * @author Gregory Boissinot
 */
@DDD.DomainEvent
public interface DomainEvent extends Event {

    String DOMAIN_EVENT_TYPE = "DOMAIN_EVENT_TYPE";

    @Override
    default String type() {
        return DOMAIN_EVENT_TYPE;
    }
}
