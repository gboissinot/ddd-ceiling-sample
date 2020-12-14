package fr.gboissinot.kernel.event.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.gboissinot.kernel.event.Event;

/**
 * Default Event Serializer
 *
 * @author Gregory Boissinot
 */
class DefaultEventSerializer<E extends Event> implements EventSerializer<E> {

    private final Gson gson;

    DefaultEventSerializer() {
        this.gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(Event.class, new EventAdapter())
                .disableHtmlEscaping()
                .create();
    }

    @Override
    public String format(Event event) {
        return gson.toJson(event);
    }
}
