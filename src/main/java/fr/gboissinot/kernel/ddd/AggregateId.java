package fr.gboissinot.kernel.ddd;

import fr.gboissinot.kernel.java.annotation.ddd.DDD;

/**
 * @author Gregory Boissinot
 */
@DDD.ValueObjectId
public interface AggregateId<R> {
    R value();
}
