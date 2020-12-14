package fr.gboissinot.kernel.event.serializer;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fr.gboissinot.kernel.event.Event;

import java.io.IOException;

/**
 * @author Gregory Boissinot
 */
class EventAdapter extends TypeAdapter<Event> {

    @Override
    public void write(final JsonWriter writer, final Event event) throws IOException {
        if (event == null) {
            writer.nullValue();
        } else {
            writer.beginObject();
            writer.name("name").value(event.name());
            writer.endObject();
        }
    }

    @Override
    public Event read(final JsonReader jsonReader) {
        return null;
    }
}
