package helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSerializer extends StdSerializer<LocalDate> {

    protected LocalDateSerializer() {
        super(LocalDate.class);
    }


    @Override
    public void serialize(LocalDate value,
                          JsonGenerator generator, SerializerProvider provider) throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        generator.writeString(formatter.format(value));

    }
}
