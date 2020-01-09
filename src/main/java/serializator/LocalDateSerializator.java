package serializator;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Class LocalDateSerializator is used to show LocalDate in "yyyy-MM-dd" format
 * @author Polina Shcherbinina
 * @version 1.1
 */
public class LocalDateSerializator extends StdSerializer<LocalDate> {

    /**
     * Constructor. Creates LocalDateSerializator object from its super class
     * @param t common parameter
     */
    public LocalDateSerializator(Class<LocalDate> t) {
        super(t);
    }


    /**
     * Constructor. Creates object LocalDateSerializator without parameters
     */
    public LocalDateSerializator() {
        this(null);
    }


    /**
     * Method serializes LocalDate to "yyyy-MM-dd" format
     * @param date local date
     * @param gen an instance object of class to define class that defines public API for writing JSON content
     * @param serializerProvider an instance object of Class that defines API used by ObjectMapper and JsonSerializers
     * to obtain serializers capable of serializing instances of specific types; as well as the default implementation
     * of the functionality.
     * @throws IOException exception is threw if I/O operations for LocalDate failed or interrupted
     */
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

