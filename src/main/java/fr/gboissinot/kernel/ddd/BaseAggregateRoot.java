package fr.gboissinot.kernel.ddd;

import fr.gboissinot.kernel.ddd.event.DomainEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Gregory Boissinot
 */
public abstract class BaseAggregateRoot<ID extends AggregateId> implements AggregateRootType<ID> {

    private final ID aggregateId;

    private long version;

    private List<DomainEvent> events = new ArrayList<>();

    protected BaseAggregateRoot(ID aggregateId, long version) {
        this.aggregateId = Objects.requireNonNull(aggregateId);
        this.version = version;
    }

    @Override
    public long version() {
        return version;
    }

    protected void incrementVersion() {
        ++version;
    }

    protected void raiseEvent(DomainEvent event) {
        this.events.add(Objects.requireNonNull(event));
    }

    @Override
    public ID id() {
        return aggregateId;
    }

    @Override
    public List<? extends DomainEvent> events() {
        return Collections.unmodifiableList(events);
    }

    @Override
    public String toString() {
        return "(" + this.getClass().getName() + "," + version + ")";
    }
}