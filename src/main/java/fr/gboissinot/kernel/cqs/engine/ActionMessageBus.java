package fr.gboissinot.kernel.cqs.engine;

/**
 * Basic message bus libs object for processing actions.
 * <p>
 * The mechanism that dispatches action messages to their appropriate ActionMessageHandler.
 * Only a single handler may be subscribed for a single action message at any time.
 * <p>
 * Several implementations can be used such as a useCase bus, a command bus or a query bus.
 *
 * @author Gregory Boissinot
 */
public interface ActionMessageBus<A extends ActionMessage> {

    /**
     * Posts a {@link ActionMessage} message into the bus and routes the message to its matching handler
     *
     * @param action the given action
     * @param <R>    the result
     * @return the action result
     * @throws ActionMessageBusException if a problem occurs.
     */
    <R> R post(A action) throws ActionMessageBusException;
}
