package fr.gboissinot.kernel.cqs.command;

import fr.gboissinot.kernel.cqs.engine.ActionMessageBus;

/**
 * Interface that describes a command bus dispatching {@link Command} messages to their appropriate {@link CommandHandler}.
 * A CommandBus is an {@link ActionMessageBus} specialization.
 *
 * @author Gregory Boissinot
 */
public interface CommandBus extends ActionMessageBus<Command> {
}
