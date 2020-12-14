package fr.gboissinot.kernel.ddd;

import java.util.Objects;

/**
 * @author Gregory Boissinot
 */
public abstract class BaseAggregateId<R> implements AggregateId<R> {

    private final R value;

    protected BaseAggregateId(R value) {
        this.value = value;
    }

    @Override
    public R value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseAggregateId that = (BaseAggregateId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BaseAggregateId{" +
                "value='" + value + '\'' +
                '}';
    }
}
