package fr.gboissinot.kernel.cqs.command;

import fr.gboissinot.kernel.cqs.engine.*;

import java.util.List;

public class CommandBusFactory {

    public static CommandBus createSimpleBus(ActionMessageHandlerResolver actionMessageHandlerResolver, List<ActionMessageProcessor> actionProcessors) {
        return new SimpleCommandBus(
                new SimpleActionMessageBusExecutor<>(
                        new ActionMessageDispatcher(
                                ActionMessageHandlersRegistry.valueOf(actionMessageHandlerResolver)), actionProcessors));
    }
}
