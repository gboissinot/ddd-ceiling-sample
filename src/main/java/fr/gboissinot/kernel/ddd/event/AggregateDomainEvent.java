package fr.gboissinot.kernel.ddd.event;

import fr.gboissinot.kernel.ddd.AggregateId;
import fr.gboissinot.kernel.ddd.AggregateRootType;

/**
 * @param <ID>
 * @param <A>
 * @author Gregory Boissinot
 */
public interface AggregateDomainEvent<ID extends AggregateId, A extends AggregateRootType> extends DomainEvent {
    Class<A> aggregateRootTypeClass();

    ID aggregateId();
}
