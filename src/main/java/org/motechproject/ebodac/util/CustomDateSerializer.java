package org.motechproject.ebodac.util;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

/**
 * Serializer for DateTime representation in UI
 */
public class CustomDateSerializer extends JsonSerializer<DateTime> {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void serialize(DateTime value, JsonGenerator gen,
                          SerializerProvider arg2)
            throws IOException {

        gen.writeString(FORMATTER.print(value));
    }
}
