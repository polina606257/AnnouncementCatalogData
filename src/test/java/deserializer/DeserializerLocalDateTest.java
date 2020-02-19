package deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConfigAppTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfigAppTest.class})
@WebAppConfiguration
@Sql(scripts = {"classpath:scripts/alter_table_ad.sql","classpath:scripts/truncate_table_ad.sql",
        "classpath:scripts/alter_table_category.sql","classpath:scripts/truncate_table_category.sql",
        "classpath:scripts/alter_table_author.sql","classpath:scripts/truncate_table_author.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class DeserializerLocalDateTest {

    @Test
    public void shouldDeserialize() throws IOException {
        LocalDate date = new ObjectMapper().readValue("2020/01/27", LocalDate.class);

        Assert.assertTrue(date.equals(LocalDate.of(2020, 01, 27)));
    }

}
