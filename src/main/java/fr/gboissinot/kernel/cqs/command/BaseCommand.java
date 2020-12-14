package fr.gboissinot.kernel.cqs.command;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Base class for all commands.
 * Provides default implementation for equals(), hashCode() and toString() methods.
 *
 * @author Gregory Boissinot
 */
public abstract class BaseCommand implements Command {

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

