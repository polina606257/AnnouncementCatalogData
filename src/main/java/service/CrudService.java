package service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {

    void save(T t);

    void update (T t);

    T getById(int id);

    void deleteById(int id);

    public List<T> findAllSort(String field);
}
