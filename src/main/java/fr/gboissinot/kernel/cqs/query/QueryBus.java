package fr.gboissinot.kernel.cqs.query;

import fr.gboissinot.kernel.cqs.command.CommandBus;
import fr.gboissinot.kernel.cqs.engine.ActionMessageBus;

/**
 * Interface that describes a query bus dispatching {@link Query} messages to their appropriate {@link QueryHandler}.
 * A QueryBus is an {@link ActionMessageBus} specialization and use the semantic valueOf a {@link CommandBus} for processing queries.
 *
 * @author Gregory Boissinot
 */
public interface QueryBus extends ActionMessageBus<Query> {
}
