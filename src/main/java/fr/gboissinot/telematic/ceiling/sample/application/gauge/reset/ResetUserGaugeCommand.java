package fr.gboissinot.telematic.ceiling.sample.application.gauge.reset;

import fr.gboissinot.kernel.cqs.command.BaseCommand;
import fr.gboissinot.kernel.java.annotation.Pattern;
import fr.gboissinot.telematic.ceiling.sample.domain.model.UserId;

import java.util.Objects;

@Pattern.Command
public class ResetUserGaugeCommand extends BaseCommand {

    public final UserId userId;

    public ResetUserGaugeCommand(UserId userId) {
        this.userId = Objects.requireNonNull(userId);
    }
}
