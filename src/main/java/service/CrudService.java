package service;

import java.util.List;

/**
 * CrudService interface binds realization part with user and contains CRUD methods with common type of data
 * @param <T> common type of data that can be used for specific type latter
 * @author Polina Shcherbinina
 * @version 1.1
 */
public interface CrudService<T> {

    /**
     * Method save object to database
     * @param t common type of data
     */
    void save(T t);


    /**
     * Method updates data already available in database
     * @param t common type of data
     */
    void update (T t);


    /**
     * Method gets object by id
     * @param id id of object
     * @return some object
     */
    T getById(int id);


    /**
     * Method deletes some object
     * @param id object id
     */
    void deleteById(int id);


    /**
     * Method gets all available objects and sort them by field
     * @param field field for sorting
     * @return sorted list of objects
     */
    public List<T> findAllSort(String field);
}
