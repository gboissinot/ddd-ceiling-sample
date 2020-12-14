package fr.gboissinot.telematic.ceiling.sample.infrastructure;

import fr.gboissinot.kernel.ddd.BaseAggregateRepository;
import fr.gboissinot.kernel.event.EventBus;
import fr.gboissinot.kernel.java.annotation.ddd.DDD;
import fr.gboissinot.telematic.ceiling.sample.domain.model.User;
import fr.gboissinot.telematic.ceiling.sample.domain.model.UserId;
import fr.gboissinot.telematic.ceiling.sample.domain.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@DDD.RepositoryImpl
@Repository
class JpaUsers extends BaseAggregateRepository<UserId, User> implements Users {

    private static final AtomicLong SEQUENCE_USER_ID = new AtomicLong(0);

    private final JpaUserDataModelRepository jpaUserDataModelRepository;

    @SuppressWarnings("unchecked")
    @Autowired
    public JpaUsers(EventBus eventBus, JpaUserDataModelRepository jpaUserDataModelRepository) {
        super(eventBus);
        this.jpaUserDataModelRepository = jpaUserDataModelRepository;
    }

    @Override
    protected void saveOrUpdate(User user) {
        UserDataModel userDataModel = UserToUserDataModeAdapter.INSTANCE.apply(user);
        jpaUserDataModelRepository.save(userDataModel);
    }

    @Override
    public List<User> all() {
        return jpaUserDataModelRepository
                .findAll()
                .stream()
                .map(UserDataModel::toUser)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return jpaUserDataModelRepository
                .findById(userId.value())
                .map(UserDataModel::toUser);
    }

    @Override
    public UserId nextIdentity() {
        return UserId.of(SEQUENCE_USER_ID.incrementAndGet());
    }
}
