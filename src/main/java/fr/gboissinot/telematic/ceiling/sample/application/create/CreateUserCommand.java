package fr.gboissinot.telematic.ceiling.sample.application.create;

import fr.gboissinot.kernel.cqs.command.BaseCommand;
import fr.gboissinot.kernel.java.annotation.Pattern;

@Pattern.Command
@SuppressWarnings("WeakerAccess")
public class CreateUserCommand extends BaseCommand {

    public final long cellingValue;

    public CreateUserCommand(long cellingValue) {
        this.cellingValue = cellingValue;
    }
}
