package fr.gboissinot.telematic.ceiling.sample.domain.model;

import fr.gboissinot.kernel.ddd.AggregateRepository;
import fr.gboissinot.kernel.java.annotation.ddd.DDD;

import java.util.List;

@DDD.Repository
public interface Users extends AggregateRepository<UserId, User> {
    List<User> all();
}
