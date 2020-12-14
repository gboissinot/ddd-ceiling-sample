package fr.gboissinot.kernel.cqs.command;

import fr.gboissinot.kernel.cqs.engine.ActionMessageHandler;

/**
 * @param <R> the result
 * @param <C> the command
 * @author Gregory Boissinot
 */
public interface CommandHandler<R, C extends Command> extends ActionMessageHandler<R, C> {
    R handle(C command);
}