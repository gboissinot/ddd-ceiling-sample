package fr.gboissinot.telematic.ceiling.sample.application.gauge.modify;

import fr.gboissinot.kernel.cqs.command.BaseCommand;
import fr.gboissinot.kernel.cqs.command.CommandPreconditionsFailureException;
import fr.gboissinot.kernel.java.annotation.Pattern;
import fr.gboissinot.telematic.ceiling.sample.domain.model.UserId;

import java.util.Objects;

/**
 * Represents the address modification command
 */
@Pattern.Command
public class ModifyUserGaugeCommand extends BaseCommand {

    public final UserId userId;
    public final double amount;

    public ModifyUserGaugeCommand(UserId userId, double amount) {
        this.userId = Objects.requireNonNull(userId);
        this.amount = amount;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void check() {
        if (amount <= 0) {
            throw new CommandPreconditionsFailureException("The modification user command amount must be positive and different of zero.");
        }
    }
}
