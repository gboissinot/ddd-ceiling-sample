package fr.gboissinot.kernel.ddd;

import fr.gboissinot.kernel.ddd.event.DomainEvent;
import fr.gboissinot.kernel.event.EventBus;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Gregory Boissinot
 */
public abstract class BaseAggregateRepository<ID extends AggregateId, A extends AggregateRootType<ID>> implements AggregateRepository<ID, A> {

    private final EventBus<DomainEvent> eventBus;

    protected BaseAggregateRepository(EventBus<DomainEvent> eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void add(A aggregate) {
        checkOptimisticLocking(Objects.requireNonNull(aggregate));
        saveOrUpdate(aggregate);
        propagateEvents(aggregate);
    }

    private void propagateEvents(A aggregate) {
        for (DomainEvent event : aggregate.events()) {
            eventBus.post(event);
        }
    }

    private void checkOptimisticLocking(A aggregate) {
        long version = aggregate.version();
        if (version != getCurrentVersion(aggregate.id())) {
            throw new OptimisticLockException();
        }
    }

    @SuppressWarnings("WeakerAccess")
    protected abstract void saveOrUpdate(A aggregate);

    private long getCurrentVersion(ID id) {
        Optional<A> optionalA = findById(id);
        return optionalA.map(AggregateRootType::version).orElse(0L);
    }
}
