package fr.gboissinot.kernel.ddd;

import fr.gboissinot.kernel.ddd.event.DomainEvent;
import fr.gboissinot.kernel.java.annotation.ddd.DDD;

import java.util.List;

/**
 * @author Gregory Boissinot
 */
@DDD.AggregateRoot
public interface AggregateRootType<ID extends AggregateId> {
    ID id();

    long version();

    List<? extends DomainEvent> events();
}