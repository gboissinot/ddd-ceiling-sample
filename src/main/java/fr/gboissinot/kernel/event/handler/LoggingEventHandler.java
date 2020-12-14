package fr.gboissinot.kernel.event.handler;

import fr.gboissinot.kernel.event.Event;
import fr.gboissinot.kernel.event.EventHandler;
import fr.gboissinot.kernel.event.serializer.EventSerializerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents the Logging Event Handler class
 *
 * @param <E> the event parameter
 * @author Gregory Boissinot
 */
public class LoggingEventHandler<E extends Event> implements EventHandler<E> {

    private static final Logger LOGGER = LogManager.getLogger(LoggingEventHandler.class);

    @SuppressWarnings("unchecked")
    @Override
    public void handle(E event) {
        LOGGER.info(EventSerializerFactory.get().format(event));
    }
}
