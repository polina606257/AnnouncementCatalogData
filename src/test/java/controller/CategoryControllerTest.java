package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConfigAppTest;
import domain.*;
import org.apache.http.auth.AUTH;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import service.AdService;
import service.AuthorService;
import service.CategoryServiceImpl;
import service.CrudService;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by polin on 1/10/2020.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfigAppTest.class})
@WebAppConfiguration
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController controller;

    @Mock
    private CrudService<Category> service;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void shouldSaveCategory() throws Exception {

        Mockito.doNothing().when(service).save(ArgumentMatchers.any(Category.class));

        String json = new ObjectMapper().writeValueAsString(new Category("Cars"));

        mockMvc.
                perform(post("/category/save")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetCategory() throws Exception {

        Category tv = new Category("TV");

        Mockito.doReturn(tv).when(service).getById(ArgumentMatchers.anyInt());

        mockMvc.
                perform(get("/category/get/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(0))
                .andExpect(jsonPath("name").value("TV"));
    }

    @Test
    public void shouldGetCategoryWithAds() throws Exception {

        Ad ad = new Ad();

        ad.setText("AdText");
        ad.setName("AdName");

        Author author = new Author();

        author.setId(3);
        author.setName("John");

        Category tv = new Category("TV");

        ad.setCategory(tv);
        ad.setAuthor(author);

        tv.addAd(ad);

        Mockito.doReturn(tv).when(service).getById(ArgumentMatchers.anyInt());

        mockMvc.
                perform(get("/category/get/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(0))
                .andExpect(jsonPath("name").value("TV"))
                .andExpect(jsonPath("ads[0].name").value(ad.getName()))
                .andExpect(jsonPath("ads[0].category.id").value(0))
                .andExpect(jsonPath("ads[0].category.category").value("TV"))
                .andExpect(jsonPath("ads[0].text").value(ad.getText())
//                .andExpect(jsonPath("phone.number").value(ad.getText())
//                .andExpect(jsonPath("phone.id").value(ad.getText())
                );
    }


}
