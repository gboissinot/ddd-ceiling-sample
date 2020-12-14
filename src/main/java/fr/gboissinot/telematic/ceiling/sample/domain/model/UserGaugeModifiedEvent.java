package fr.gboissinot.telematic.ceiling.sample.domain.model;

import fr.gboissinot.kernel.ddd.event.BaseAggregateDomainEvent;
import fr.gboissinot.kernel.java.annotation.ddd.DDD;

/**
 * Represents the modified user gauge event
 */
@DDD.DomainEvent
public class UserGaugeModifiedEvent extends BaseAggregateDomainEvent<UserId, User> {

    private final double amount;

    UserGaugeModifiedEvent(UserId aggregateId, double amount) {
        super(User.class, aggregateId);
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }
}