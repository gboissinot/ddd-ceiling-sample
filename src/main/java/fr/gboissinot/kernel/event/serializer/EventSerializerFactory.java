package fr.gboissinot.kernel.event.serializer;

/**
 * @author Gregory Boissinot
 */
public class EventSerializerFactory {

    private static final EventSerializer INSTANCE = new DefaultEventSerializer();

    private EventSerializerFactory() {
    }

    public static EventSerializer get() {
        return INSTANCE;
    }
}
