package service;

import domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.CategoryRepository;
import java.util.List;

/**
 * Class CategoryServiceImpl implements all methods from {@link CrudService} for Category
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Service
public class CategoryServiceImpl implements CrudService<Category>{

    /**
     * Field repository is an object of {@link CategoryRepository}, it has all CRUD methods for Category
     */
    @Autowired
    private CategoryRepository repository;


    /**
     * Default constructor. Creates CategoryServiceImpl object without parameters
     */
    public CategoryServiceImpl() {
    }


    /**
     * Method save new category to database
     * @param category category of advertisement
     */
    @Override
    public void save(Category category) {
        repository.save(category);
    }


    /**
     * Method updates already available category
     * @param category a category with id of category we want to update and new parameters for setting up {@link Category}
     */
    @Override
    public void update(Category category) {
        repository.save(category);
    }


    /**
     * Method gets category by id
     * @param id category id
     * @return category with input id
     */
    @Override
    public Category getById(int id) {
       return repository.findById(id).get();
    }


    /**
     * Method gets sorted list of all categories by field {@link Category}
     * @param field field of category
     * @return sorted list of all categories by input field
     */
    @Override
    public List<Category> findAllSort(String field) {//"id", "name"
        Sort sort = Sort.by(Sort.Order.asc(field));
        return repository.findAll(sort);
    }


    /**
     * Method deletes category by id
     * @param id category id
     */
    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
