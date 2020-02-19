package controller;

import domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import service.CrudService;
import java.util.List;

/**
 * Class CategoryController using to perform category CRUD methods for user implementation
 * @author Polina Shcherbinina
 * @version 1.1
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    /**
     * Service is object instance of {@link CrudService} interface with Category type. It connects realization part with
     * user application
     */
    private final CrudService<Category> service;

    @Autowired
    public CategoryController(@Qualifier("categoryServiceImpl") CrudService<Category> service) {
        this.service = service;
    }


    /**
     * Method saves new category of advertisement
     * @param category new category of advertisement {@link Category}
     */
    @PostMapping("/save")
    public void save(@RequestBody Category category) {
        service.save(category);
    }

    /**
     * Method updates already available category
     * @param category category with id of category we want to update and new parameters for setting up {@link Category}
     */
    @PostMapping("/update")
    public void update(@RequestBody Category category) {
        service.update(category);
    }

    /**
     * Method gets category by id
     * @param id category id
     * @return category with input id
     */
    @GetMapping("/get/{id}")
    public Category get(@PathVariable int id) {
        return service.getById(id);
    }


    /**
     * Method gets sorted list of all categories by field {@link Category}
     * @param field field of category
     * @return sorted list of all categories by input field
     */
    @GetMapping("/get/all/sort/{field}")
    public List<Category> getAllSort(@PathVariable("field") String field) {
        return service.findAllSort(field);
    }


    /**
     * Method deletes category by id
     * @param id category id
     */
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }
}

