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
import service.AdService;
import service.AuthorService;
import service.CrudService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfigAppTest.class})
@WebAppConfiguration
@Sql(scripts = {"classpath:scripts/alter_table_ad.sql", "classpath:scripts/truncate_table_ad.sql",
        "classpath:scripts/alter_table_category.sql", "classpath:scripts/truncate_table_category.sql",
        "classpath:scripts/alter_table_author.sql", "classpath:scripts/truncate_table_author.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AdServiceTest {

    @Autowired
    private AdService adService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CrudService<Category> categoryService;

    @Autowired
    private AdRepository repository;

    @Before
    public void saveAd() {
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
    }

    @Test
    public void shouldSaveAd() {

        Ad ad = repository.findById(1).get();

        Ad adTest = adService.getById(1);

        Assert.assertTrue(ad.equals(adTest));
    }


    @Test
    public void shouldUpdateAd() {

        Ad ad = repository.findById(1).get();

        Author author = ad.getAuthor();

        Category category = ad.getCategory();

        ad.setText("new text");
        ad.getCategory().setName("TV");

        adService.update(ad);

        Ad adTest = adService.getById(1);

        Assert.assertTrue(ad.equals(adTest));
    }

    @Test
    public void shouldGetById() {

        Ad ad = repository.findById(1).get();
        Category category = ad.getCategory();
        Author author = ad.getAuthor();

        Ad adTest = adService.getById(1);

        Assert.assertTrue(adTest.getName().equals("Red car") && adTest.getText().equals("Buy new red car") &&
                adTest.getPrice().equals(new BigDecimal("120000.00")) && adTest.getAuthor().getId() == author.getId()
                && adTest.getCategory().getId() == category.getId());
    }

    @Test
    public void shouldFindAllSort() {

        Ad ad = repository.findById(1).get();
        Author author = ad.getAuthor();
        Category category = ad.getCategory();

        Ad ad2 = new Ad("Blue car", LocalDate.now(), "Buy new red car", new BigDecimal("120000.25"), true,
                author, category);
        adService.save(ad2);

        Ad ad3 = new Ad("Yellow car", LocalDate.now(), "Buy new red car", new BigDecimal("120000.25"), true,
                author, category);
        adService.save(ad3);

        List<Ad> adList = adService.findAllSort("name");

        Assert.assertTrue(adList.get(0).equals(ad2) && adList.get(1).equals(ad) &&
                adList.get(2).equals(ad3));
    }

    @Test
    public void shouldDeleteById() {

        adService.deleteById(1);

        List<Ad> adList = repository.findAll();

        Assert.assertTrue(adList.isEmpty());
    }

    @Test
    public void shouldGetByCategory() {

        Ad ad = repository.findById(1).get();
        Category category = ad.getCategory();
        Author author = ad.getAuthor();

        Ad ad2 = new Ad("Blue car", LocalDate.now(), "Buy new red car", new BigDecimal("120000.25"), true,
                author, category);
        adService.save(ad2);

        Ad ad3 = new Ad("1-bedroom apartment", LocalDate.now(), "Buy apartment", new BigDecimal("1200000.25"),
                true, author, new Category("House"));

        List<Ad> adList = adService.getByCategory(1);

        Assert.assertTrue(adList.size() == 2 && adList.get(0).equals(ad) && adList.get(1).equals(ad2));
    }

    @Test
    public void getByCategories() {

        Ad ad = repository.findById(1).get();
        Author author = ad.getAuthor();

        Category category1 = new Category("House");
        categoryService.save(category1);

        Category category2 = new Category("Toys");
        categoryService.save(category2);

        Ad ad2 = new Ad("Blue rabbit", LocalDate.now(), "Buy new blue rabbit", new BigDecimal("12.25"),
                true, author, category2);
        adService.save(ad2);

        Ad ad3 = new Ad("apartment", LocalDate.now(), "Buy apartment", new BigDecimal("1200000.25"),
                true, author, category1);
        adService.save(ad3);

        List<Integer> categoriesIds = new LinkedList();
        categoriesIds.add(1);
        categoriesIds.add(2);

        List<Ad> adList = adService.getByCategories(categoriesIds);

        Assert.assertTrue(adList.size() == 2 && adList.get(0).equals(ad) && adList.get(1).equals(ad3));
    }

    @Test
    public void shouldGetAll() {

        Ad ad = repository.findById(1).get();
        Author author = ad.getAuthor();
        Category category = ad.getCategory();

        Ad ad1 = new Ad("yellow car", LocalDate.now(), "Buy yellow car", new BigDecimal("1200000.25"),
                true, author, category);
        adService.save(ad1);

        List<Ad> all = adService.getAll();

        Assert.assertTrue(all.size() == 2 && all.get(0).equals(ad) && all.get(1).equals(ad1));
    }

    @Test
    public void shouldGetByAuthor() {

        Ad ad = repository.findById(1).get();
        Category category = ad.getCategory();

        Author author1 = new Author("Maxim", new Phone("9029809021"), new Address(Country.RUSSIA, City.MOSCOW),
                new Email("maxim@gmail"));
        authorService.save(author1);

        Ad ad1 = new Ad("yellow car", LocalDate.now(), "Buy yellow car", new BigDecimal("1200000.25"),
                true, author1, category);
        adService.save(ad1);

        Ad ad2 = new Ad("orange car", LocalDate.now(), "Buy orange car", new BigDecimal("1200000.25"),
                true, author1, category);
        adService.save(ad2);

        List<Ad> adList = adService.getByAuthor(2);

        Assert.assertTrue(adList.size() == 2 && adList.get(0).equals(ad1) && adList.get(1).equals(ad2));
    }

    @Test
    public void shouldGetByDate() {

        Ad ad = repository.findById(1).get();
        Author author = ad.getAuthor();
        Category category = ad.getCategory();

        Ad ad1 = new Ad("yellow car", LocalDate.of(2020, 01, 21), "Buy yellow car",
                new BigDecimal("1200000.25"), true, author, category);
        adService.save(ad1);

        List<Ad> adList = adService.getByDate(LocalDate.now());

        Assert.assertTrue(adList.size() == 1 && adList.get(0).equals(ad));
    }

    @Test
    public void shouldGetByKeyWord() {

        Ad ad = repository.findById(1).get();
        Category category = ad.getCategory();
        Author author = ad.getAuthor();

        Ad ad1 = new Ad("yellow car", LocalDate.of(2020, 01, 21), "Buy yellow car",
                new BigDecimal("1200000.25"), true, author, category);
        adService.save(ad1);

        Ad ad2 = new Ad("yellow truck", LocalDate.now(), "Buy yellow car", new BigDecimal("1200000.25"),
                true, author, category);
        adService.save(ad2);

        List<Ad> adList = adService.getByKeyWord("car");

        List<Ad> adList1 = adService.getByKeyWord("yellow car");

        List<Ad> adList2 = adService.getByKeyWord("House");

        Assert.assertTrue(adList.size() == 2 && adList.get(0).equals(ad) && adList.get(1).equals(ad1));

        Assert.assertTrue(adList1.size() == 1 && adList1.get(0).equals(ad1));

        Assert.assertTrue(adList2.isEmpty());
    }

    @Test
    public void shouldFindAllPagination() {
        Ad ad = repository.findById(1).get();
        Category category = ad.getCategory();
        Author author = ad.getAuthor();

        Ad ad1 = new Ad("yellow car", LocalDate.of(2020, 01, 21), "Buy yellow car",
                new BigDecimal("1200000.25"), true, author, category);
        adService.save(ad1);

        Ad ad2 = new Ad("yellow truck", LocalDate.now(), "Buy yellow car", new BigDecimal("1200000.25"),
                true, author, category);
        adService.save(ad2);

        List<Ad> adList = adService.findAllPagination();

        Assert.assertTrue(adList.size() == 2 && adList.get(0).equals(ad) && adList.get(1).equals(ad1));
    }

    @Test
    public void shouldDeleteInactiveAds() {
        Ad ad = repository.findById(1).get();
        Author author = ad.getAuthor();
        Category category = ad.getCategory();

        Ad ad1 = new Ad("Red car", LocalDate.now(), "Buy new red car", new BigDecimal("120000.00"),
                false, author, category);
        adService.save(ad);

        adService.deleteInactiveAds();

        List<Ad> adList = adService.getAll();

        Assert.assertTrue(adList.size() == 1 && adList.get(0).equals(ad));
    }
}

