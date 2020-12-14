package fr.gboissinot.kernel.cqs.query;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Base class for all queries.
 * Provides default implementation for equals(), hashCode() and toString() methods.
 *
 * @author Gregory Boissinot
 */
public abstract class BaseQuery implements Query {

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
        return name();
    }
}
