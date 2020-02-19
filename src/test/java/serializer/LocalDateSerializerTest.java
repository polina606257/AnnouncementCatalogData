package serializer;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import config.ConfigAppTest;
import domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import serializator.LocalDateSerializator;
import service.AdService;
import service.AuthorService;
import service.CrudService;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfigAppTest.class})
@WebAppConfiguration
@Sql(scripts = {"classpath:scripts/alter_table_ad.sql","classpath:scripts/truncate_table_ad.sql",
        "classpath:scripts/alter_table_category.sql","classpath:scripts/truncate_table_category.sql",
        "classpath:scripts/alter_table_author.sql","classpath:scripts/truncate_table_author.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class LocalDateSerializerTest {
    @Autowired
    private CrudService<Category> categoryService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AdService adService;

    @Test
    public void shouldSerialize() throws JsonProcessingException, IOException {
        Category category = new Category("Cars");
        categoryService.save(category);

        Phone phone = new Phone("06713029130");
        Email email = new Email("val@gmail.com");
        Address address = new Address(Country.RUSSIA, City.MOSCOW);
        Author author = new Author("John", phone, address, email);
        authorService.save(author);

        Ad ad = new Ad("Red car", LocalDate.now(), "Buy new red car", new BigDecimal("120000.00"),
                true, author, category);


//        adService.save(ad);

//        String localDate = new ObjectMapper().writeValueAsString(ad);
//        System.out.println();
    }
}

