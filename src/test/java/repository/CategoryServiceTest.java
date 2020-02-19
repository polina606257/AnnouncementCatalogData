package repository;

import config.ConfigAppTest;
import domain.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import service.CrudService;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfigAppTest.class})
@WebAppConfiguration
@Sql(scripts = {"classpath:scripts/alter_table_category.sql","classpath:scripts/truncate_table_category.sql",
        "classpath:scripts/alter_table_ad.sql", "classpath:scripts/truncate_table_ad.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CategoryServiceTest {

    @Autowired
    private CrudService<Category> categoryService;

    @Autowired
    private CategoryRepository repository;

    @Before
    public void saveCategory() {
        Category category = new Category("Cars");
        categoryService.save(category);
    }

    @Test
    public void shouldSaveCategory() {

        Category category = repository.findById(1).get();
        Category categoryTest = categoryService.getById(1);

        Assert.assertTrue(category.getName().equals(categoryTest.getName()));
    }

    @Test
    public void shouldUpdateCategory() {

        Category categoryNew = new Category(1, "Shoe");
        categoryService.update(categoryNew);

        Category categoryTest = categoryService.getById(1);

        Assert.assertTrue(categoryNew.getName().equals(categoryTest.getName()));
    }

    @Test
    public void shouldGetById() {

        Category categoryTest = categoryService.getById(1);

        Assert.assertTrue(categoryTest.getName().equals("Cars"));
    }

    @Test
    public void shouldFindAllSort() {

        Category category2 = new Category("Toys");
        categoryService.save(category2);

        Category category3 = new Category("Houses");
        categoryService.save(category3);

        List<Category> categoriesList = categoryService.findAllSort("name");

        Category categoryTest1 = categoryService.getById(1);
        Category categoryTest2 = categoryService.getById(2);
        Category categoryTest3 = categoryService.getById(3);

        Assert.assertTrue(categoriesList.get(0).getName().equals(categoryTest1.getName()) &&
                categoriesList.get(1).getName().equals(categoryTest3.getName()) &&
                categoriesList.get(2).getName().equals(categoryTest2.getName()));
    }

    @Test
    public void shouldDeleteById() {

        categoryService.deleteById(1);

        List<Category> categoryList = repository.findAll();

        Assert.assertTrue(categoryList.isEmpty());

    }
}
