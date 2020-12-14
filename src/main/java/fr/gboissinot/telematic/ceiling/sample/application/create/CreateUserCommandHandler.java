package fr.gboissinot.telematic.ceiling.sample.application.create;

import fr.gboissinot.kernel.cqs.command.CommandHandler;
import fr.gboissinot.telematic.ceiling.sample.domain.model.User;
import fr.gboissinot.telematic.ceiling.sample.domain.model.UserId;
import fr.gboissinot.telematic.ceiling.sample.domain.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateUserCommandHandler implements CommandHandler<UserId, CreateUserCommand> {

    private final Users users;

    @Autowired
    CreateUserCommandHandler(Users users) {
        this.users = users;
    }

    @Override
    public UserId handle(CreateUserCommand createUserCommand) {
        UserId userId = users.nextIdentity();
        users.add(User
                .withUserId(userId)
                .withCeiling(createUserCommand.cellingValue)
                .build());
        return userId;
    }
}
