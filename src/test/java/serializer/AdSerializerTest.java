package serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConfigAppTest;
import domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import service.AdService;
import service.AuthorService;
import service.CrudService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfigAppTest.class})
@WebAppConfiguration
@Sql(scripts = {"classpath:scripts/alter_table_ad.sql","classpath:scripts/truncate_table_ad.sql",
        "classpath:scripts/alter_table_category.sql","classpath:scripts/truncate_table_category.sql",
        "classpath:scripts/alter_table_author.sql","classpath:scripts/truncate_table_author.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AdSerializerTest {
    @Autowired
    private CrudService<Category> categoryService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AdService adService;

    @Test
    public void shouldSerialize() throws IOException{
        Category category = new Category("Cars");
        categoryService.save(category);

        Phone phone = new Phone("06713029130");
        Email email = new Email("val@gmail.com");
        Address address = new Address(Country.RUSSIA, City.MOSCOW);
        Author author = new Author("John", phone, address, email);
        authorService.save(author);

        Ad ad = new Ad("Red car", LocalDate.now(), "Buy new red car", new BigDecimal("120000.00"),
                true, author, category);
        adService.save(ad);

        String serialized = new ObjectMapper().writeValueAsString(ad);

       // Assert.assertTrue();
    }



}
