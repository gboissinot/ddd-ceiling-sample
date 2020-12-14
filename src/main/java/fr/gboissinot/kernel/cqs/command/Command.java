package fr.gboissinot.kernel.cqs.command;

import fr.gboissinot.kernel.cqs.engine.ActionMessage;

/**
 * Command object.
 * <p>
 * A command is a request for the system to perform that changes the state valueOf the system.
 * Commands are imperative. They represent mutable operations.
 *
 * @author Gregory Boissinot
 */
public interface Command extends ActionMessage {

    @Override
    default void check() throws CommandPreconditionsFailureException {
    }
}
