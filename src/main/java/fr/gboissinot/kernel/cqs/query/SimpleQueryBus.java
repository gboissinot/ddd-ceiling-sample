package fr.gboissinot.kernel.cqs.query;

import fr.gboissinot.kernel.cqs.engine.ActionMessageBusProcessor;
import fr.gboissinot.kernel.cqs.engine.SimpleActionMessageBusExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * Spring {@link QueryBus} implementation
 *
 * @author Gregory Boissinot
 */
class SimpleQueryBus implements QueryBus {

    private static final Logger logger = LogManager.getLogger(ActionMessageBusProcessor.class);

    private final SimpleActionMessageBusExecutor messageBusExecutor;

    SimpleQueryBus(SimpleActionMessageBusExecutor<Query> messageBusExecutor) {
        this.messageBusExecutor = messageBusExecutor;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> R post(Query query) {
        Query q = Objects.requireNonNull(query);
        if (logger.isDebugEnabled()) {
            logger.debug("Posting {} into the QueryBus.", q.name());
        }
        return (R) messageBusExecutor.execute(q);
    }
}
