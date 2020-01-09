package deserializator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Class LocalDateDeserializator is used to convert "yyyy-MM-dd" format of date to LocalDate
 * @author Polina Shcherbinina
 * @version 1.1
 */
public class LocalDateDeserializator extends StdDeserializer<LocalDate> {

    /**
     * Constructor. Creates LocalDateDeserializator object from its super class
     */
    public LocalDateDeserializator() {
        super(LocalDate.class);
    }


    /**
     * Method deserialize "yyyy-MM-dd" format of date to LocalDate
     * @param p is an instance object of class to define public API for reading Json content.
     * @param deserializationContext - is an instance object of a class used to allow passing in configuration settings
     * and reusable temporary objects
     * @return LocalDate
     * @throws IOException exception throws if I/O operations for LocalDate failed or interrupted
     * @throws JsonProcessingException exception throws  for all problems encountered when processing (parsing,
     * generating) JSON content that are not pure I/O problems
     */
    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        String value = node.textValue();
        LocalDate date = null;

        if (Objects.nonNull(value)) {
            date = LocalDate.parse(value);
        }

        return date;
    }
}
