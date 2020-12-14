package fr.gboissinot.telematic.ceiling.sample.application.gauge.reset;

import fr.gboissinot.kernel.cqs.command.CommandHandler;
import fr.gboissinot.telematic.ceiling.sample.application.UserNotFoundException;
import fr.gboissinot.telematic.ceiling.sample.domain.model.User;
import fr.gboissinot.telematic.ceiling.sample.domain.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResetUserGaugeCommandHandler implements CommandHandler<Void, ResetUserGaugeCommand> {

    private final Users users;

    @Autowired
    ResetUserGaugeCommandHandler(Users users) {
        this.users = users;
    }

    /**
     * Handles the gauge reset of an existing user
     *
     * @param command the user gauge reset command object
     * @throws UserNotFoundException if the user is not found
     */
    @Override
    public Void handle(ResetUserGaugeCommand command) {
        User user = users
                .findById(command.userId)
                .orElseThrow(() -> new UserNotFoundException(command.userId));
        user.resetGauge();
        users.add(user);
        return null;
    }
}
