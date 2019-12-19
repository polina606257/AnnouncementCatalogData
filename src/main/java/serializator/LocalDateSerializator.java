package serializator;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class LocalDateSerializator extends StdSerializer<LocalDate> {
    public LocalDateSerializator(Class<LocalDate> t) {
        super(t);
    }

    public LocalDateSerializator() {
        this(null);
    }

    @Override
    public void serialize(LocalDate date, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateFormat = simpleDateFormat.format(date);
        gen.writeStartObject();
        gen.writeObjectField("date", dateFormat);
        gen.writeEndObject();
    }
}

