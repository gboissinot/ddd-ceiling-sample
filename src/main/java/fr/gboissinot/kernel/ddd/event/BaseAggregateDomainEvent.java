package fr.gboissinot.kernel.ddd.event;

import fr.gboissinot.kernel.ddd.AggregateId;
import fr.gboissinot.kernel.ddd.AggregateRootType;
import fr.gboissinot.kernel.event.BaseEvent;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 * @param <ID>
 * @param <A>
 * @author Gregory Boissinot
 */
public abstract class BaseAggregateDomainEvent<ID extends AggregateId, A extends AggregateRootType> extends BaseEvent implements AggregateDomainEvent<ID, A> {

    private Class<A> aggregateRootTypeClass;
    private ID aggregateId;

    public BaseAggregateDomainEvent(Class<A> aggregateRootTypeClass, ID aggregateId) {
        this.aggregateRootTypeClass = Objects.requireNonNull(aggregateRootTypeClass);
        this.aggregateId = Objects.requireNonNull(aggregateId);
    }

    @Override
    public Class<A> aggregateRootTypeClass() {
        return aggregateRootTypeClass;
    }

    @Override
    public ID aggregateId() {
        return aggregateId;
    }

    @SuppressWarnings("all")
    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
