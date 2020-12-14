package fr.gboissinot.kernel.cqs.query;

import fr.gboissinot.kernel.cqs.engine.*;

import java.util.List;

public class QueryBusFactory {

    public static QueryBus createSimpleBus(ActionMessageHandlerResolver actionMessageHandlerResolver, List<ActionMessageProcessor> actionProcessors) {
        return new SimpleQueryBus(
                new SimpleActionMessageBusExecutor<>(
                        new ActionMessageDispatcher(
                                ActionMessageHandlersRegistry.valueOf(actionMessageHandlerResolver)), actionProcessors));
    }
}
