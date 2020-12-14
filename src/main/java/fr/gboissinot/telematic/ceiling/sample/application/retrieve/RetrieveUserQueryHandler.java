package fr.gboissinot.telematic.ceiling.sample.application.retrieve;

import fr.gboissinot.kernel.cqs.query.QueryHandler;
import fr.gboissinot.telematic.ceiling.sample.domain.model.User;
import fr.gboissinot.telematic.ceiling.sample.domain.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RetrieveUserQueryHandler implements QueryHandler<List<User>, RetrieveUserQuery> {

    private final Users users;

    @Autowired
    RetrieveUserQueryHandler(Users users) {
        this.users = users;
    }

    /**
     * Retrieves all users
     *
     * @param query the retrieve user query object
     * @return the client list
     */
    @Override
    public List<User> handle(RetrieveUserQuery query) {
        return Collections.unmodifiableList(users.all());
    }
}
