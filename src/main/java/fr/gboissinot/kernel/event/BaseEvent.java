package fr.gboissinot.kernel.event;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 * Abstract class for an {@link Event}
 * Provides by default the required elements : an identifier, a version and a occurred date
 *
 * @author Gregory Boissinot
 */
public abstract class BaseEvent implements Event {

    private static final long DOMAIN_EVENT_VERSION = 1L;

    private final String id;
    private final Long version;
    private final LocalDateTime occurredOn;

    protected BaseEvent() {
        this.id = EventIDProvider.getInstance().get();
        this.version = DOMAIN_EVENT_VERSION;
        this.occurredOn = LocalDateTime.now();
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public long version() {
        return version;
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
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
