package fr.gboissinot.telematic.ceiling.sample.domain.model;

import fr.gboissinot.kernel.ddd.event.BaseAggregateDomainEvent;
import fr.gboissinot.kernel.java.annotation.ddd.DDD;

@DDD.DomainEvent
@SuppressWarnings("WeakerAccess")
public class UserGaugeResetEvent extends BaseAggregateDomainEvent<UserId, User> {

    UserGaugeResetEvent(UserId aggregateId) {
        super(User.class, aggregateId);
    }
}
