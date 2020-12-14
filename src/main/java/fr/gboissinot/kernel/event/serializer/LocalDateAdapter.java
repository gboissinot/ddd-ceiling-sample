package fr.gboissinot.kernel.event.serializer;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

/**
 * {@link LocalDate} {@link Gson} Serializer
 *
 * @author Gregory Boissinot
 */
public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    @Override
    public void write(final JsonWriter jsonWriter, final LocalDate localDate) throws IOException {
        if (localDate == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(localDate.toString());
        }
    }

    @Override
    public LocalDate read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            return null;
        } else {
            return LocalDate.parse(jsonReader.nextString());
        }
    }
}
