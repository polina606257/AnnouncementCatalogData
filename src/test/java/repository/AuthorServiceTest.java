package repository;

import config.ConfigAppTest;
import domain.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import service.AuthorService;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfigAppTest.class})
@WebAppConfiguration
@Sql(scripts = {"classpath:scripts/alter_table_author.sql","classpath:scripts/truncate_table_author.sql",
        "classpath:scripts/alter_table_suitable_ad.sql","classpath:scripts/truncate_table_suitable_ad.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AuthorServiceTest {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private SuitableAdRepository adRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Before
    public void saveAuthor() {
        Author author = new Author("Liza", new Phone("9031907867"), new Address(Country.RUSSIA, City.MOSCOW),
                new Email("liza.but333@gmail.com"));
        authorService.save(author);
    }

    @Test
    public void shouldSaveAuthor() {
        Author author = authorRepository.findById(1).get();

        Author authorTest = authorService.getById(1);

        Assert.assertTrue(author.equals(authorTest));
    }

    @Test
    public void shouldUpdateAuthor() {

        Author updatedAuthor = new Author(1, "Maxim", new Phone("9031907867"),
                new Address(Country.RUSSIA, City.MOSCOW), new Email("maxim@gmail.com"));
        authorService.update(updatedAuthor);

        Author authorTest = authorService.getById(1);

        Assert.assertTrue(updatedAuthor.equals(authorTest));
    }

    @Test
    public void shouldGetById() {

        Author authorTest = authorService.getById(1);

        Assert.assertTrue(authorTest.getName().equals("Liza") && authorTest.getPhone().getNumber().equals("9031907867")
        && authorTest.getEmail().getEmail().equals("liza.but333@gmail.com") &&
                authorTest.getAddress().getCountry().equals(Country.RUSSIA) &&
                authorTest.getAddress().getCity().equals(City.MOSCOW));
    }

    @Test
    public void shouldFindAllSort() {

        Author author2 = new Author("Olga", new Phone("9031907867"), new Address(Country.RUSSIA, City.MOSCOW),
                new Email("olga@gmail.com"));
        authorService.save(author2);

        Author author3 = new Author("Maxim", new Phone("9031901111"), new Address(Country.USA, City.CHICAGO),
                new Email("maxim@gmail.com"));
        authorService.save(author3);

        List<Author> authorList = authorService.findAllSort("name");

        Author authorTest1 = authorService.getById(1);
        Author authorTest2 = authorService.getById(2);
        Author authorTest3 = authorService.getById(3);

        Assert.assertTrue(authorList.get(0).equals(authorTest1) && authorList.get(1).equals(authorTest3) &&
                authorList.get(2).equals(authorTest2));
    }

    @Test
    public void shouldDeleteById() {

        authorService.deleteById(1);

        List<Author> authorList = authorRepository.findAll();

        Assert.assertTrue(authorList.isEmpty());
    }

    @Test
     public void shouldSaveSuitableAd() {

        Author author = authorRepository.findById(1).get();

        SuitableAd suitableAd = new SuitableAd("apartment", BigDecimal.valueOf(100000.00),
                BigDecimal.valueOf(1000000.00), "house", author);
        authorService.saveSuitableAd(suitableAd);

        SuitableAd suitableAdTest = adRepository.findById(1);

        Assert.assertTrue(suitableAd.equals(suitableAdTest));
    }

    @Test
    public void shouldDeleteSuitableAd(){

        Author author = authorRepository.findById(1).get();

        SuitableAd suitableAd = new SuitableAd("apartment", BigDecimal.valueOf(100000.00),
                BigDecimal.valueOf(1000000.00), "house", author);
        authorService.saveSuitableAd(suitableAd);

        adRepository.deleteById(1);

        SuitableAd suitableAdTest = adRepository.findById(1);

        Assert.assertTrue(suitableAdTest == null);

    }
}

