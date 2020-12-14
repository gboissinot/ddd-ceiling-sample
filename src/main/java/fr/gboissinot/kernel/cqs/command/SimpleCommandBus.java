package fr.gboissinot.kernel.cqs.command;

import fr.gboissinot.kernel.cqs.engine.ActionMessageBusProcessor;
import fr.gboissinot.kernel.cqs.engine.SimpleActionMessageBusExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * Spring {@link CommandBus} implementation
 *
 * @author Gregory Boissinot
 */
class SimpleCommandBus implements CommandBus {

    private static final Logger logger = LogManager.getLogger(ActionMessageBusProcessor.class);

    private final SimpleActionMessageBusExecutor<Command> messageBusExecutor;

    SimpleCommandBus(SimpleActionMessageBusExecutor<Command> messageBusExecutor) {
        this.messageBusExecutor = messageBusExecutor;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> R post(Command command) {
        Command c = Objects.requireNonNull(command);
        if (logger.isDebugEnabled()) {
            logger.debug("Posting {} into the CommandBus.", c.name());
        }
        return (R) messageBusExecutor.execute(c);
    }
}

