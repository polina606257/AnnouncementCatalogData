package serializator;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import domain.Ad;
import java.io.IOException;

/**
 * Class AdSerializator is used to show Ad in "id, name, text, price, date, category{id, category}, author {id, name}"
 * format
 * @author Polina Shcherbinina
 * @version 1.1
 */
public class AdSerializator extends StdSerializer<Ad> {

    /**
     * Constructor. Creates AdSerializator object from its super class
     * @param t common parameter
     */
    public AdSerializator(Class<Ad> t) {
        super(t);
    }


    /**
     * Constructor. Creates object AdSerializator without parameters
     */
    public AdSerializator() {
        this(null);
    }


    /**
     * Method serializes Ad to "id, name, text, price, date, category{id, category}, author {id, name}" format
     * @param ad advertisement
     * @param gen is the instance object of class to define class that defines public API for writing JSON content
     * @param serializerProvider instance object of Class that defines API used by ObjectMapper and JsonSerializers
     * to obtain serializers capable of serializing instances of specific types; as well as the default implementation
     * of the functionality.
     * @throws IOException exception is threw if I/O operations for Ad failed or interrupted
     */
    @Override
    public void serialize(Ad ad, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
            gen.writeObjectField("ad_id", ad.getId());
            gen.writeStringField("name", ad.getName());
            gen.writeStringField("text", ad.getText());
            gen.writeObjectField("price", ad.getPrice());
            gen.writeObjectField("date", ad.getDate());
            gen.writeObjectFieldStart("category");//{
                gen.writeObjectField("category_id", ad.getCategory().getId());
                gen.writeStringField("category", ad.getCategory().getCategory());

            gen.writeEndObject();

            gen.writeObjectFieldStart("author");
                gen.writeObjectField("author_id", ad.getAuthor().getId());
                gen.writeStringField("name", ad.getAuthor().getName());

            gen.writeEndObject();

        gen.writeEndObject();
    }
}

