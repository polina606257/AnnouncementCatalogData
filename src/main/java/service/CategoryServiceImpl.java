package service;

import domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.CategoryRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CrudService<Category>{

    @Autowired
    private CategoryRepository repository;

    public CategoryServiceImpl() {
    }

    @Override
    public void save(Category category) {
        repository.save(category);
    }

    @Override
    public void update(Category category) {
        repository.save(category);
    }

    @Override
    public Category getById(int id) {
       return repository.findById(id).get();
    }


    public List<Category> findAllSort(String field) {//"id", "name"
        Sort sort = Sort.by(Sort.Order.asc(field));
        return repository.findAll(sort);
    }


    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
