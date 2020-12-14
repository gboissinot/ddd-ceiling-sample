package fr.gboissinot.telematic.ceiling.sample.domain.model;

import fr.gboissinot.kernel.ddd.BaseAggregateId;
import fr.gboissinot.kernel.java.annotation.ddd.DDD;

@DDD.ValueObjectId
public class UserId extends BaseAggregateId<Long> {

    private UserId(long value) {
        super(value);
    }

    public static UserId of(long value) {
        return new UserId(value);
    }
}
