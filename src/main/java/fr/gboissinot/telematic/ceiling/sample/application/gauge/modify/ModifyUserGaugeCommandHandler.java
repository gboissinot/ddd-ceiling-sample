package fr.gboissinot.telematic.ceiling.sample.application.gauge.modify;

import fr.gboissinot.kernel.cqs.command.CommandHandler;
import fr.gboissinot.telematic.ceiling.sample.application.UserNotFoundException;
import fr.gboissinot.telematic.ceiling.sample.domain.model.User;
import fr.gboissinot.telematic.ceiling.sample.domain.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ModifyUserGaugeCommandHandler implements CommandHandler<Void, ModifyUserGaugeCommand> {

    private final Users users;

    @Autowired
    ModifyUserGaugeCommandHandler(Users users) {
        this.users = users;
    }

    /**
     * Handles the gauge modification of an existing user
     *
     * @param command the  user modification gauge command object
     * @throws UserNotFoundException if the user is not found
     */
    @Override
    public Void handle(ModifyUserGaugeCommand command) {
        User user = users
                .findById(command.userId)
                .orElseThrow(() -> new UserNotFoundException(command.userId));
        user.increaseGaugeValue(command.amount);
        users.add(user);
        return null;
    }
}
