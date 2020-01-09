package service;

import domain.Ad;
import java.time.LocalDate;
import java.util.List;

/**
 * AdService interface binds realization part with user, contains methods extended from {@link CrudService} for Ad
 * type and additional methods for ad
 * @param <M> common type of data that can be used for specific type latter
 * @author Polina Shcherbinina
 * @version 1.1
 */
public interface AdService<M> extends CrudService<Ad> {

    /**
     * Method gets ad by category id
     * @param id category id
     * @return list of ads related to this category
     */
    List<Ad> getByCategory(int id);


    /**
     * Method gets ads by categories ids
     * @param ids ids of categories
     * @return list of ads related to all those categories
     */
    List<Ad> getByCategories(List<Integer> ids);


    /**
     * Method gets all available ads
     * @return list of all ads
     */
    List<Ad> getAll();


    /**
     * Method gets all available ads in sorted order by field parameter
     * @param field field for sorting
     * @return list of all available ads in sorted order
     */
    List<Ad> findAllSort(String field);


    /**
     * Method gets ads by author id
     * @param id author id
     * @return list of ads related to this author
     */
    List<Ad> getByAuthor(int id);


    /**
     * Method gets ads by date of creation
     * @param date ad date of creation
     * @return list of ads if they were created in input date
     */
    List<Ad> getByDate(LocalDate date);


    /**
     * Method gets ads if input word is containing in their name
     * @param word word that has to be contained in ad name
     * @return list of ads where ad name contains input word
     */
    List<Ad> getByKeyWord(String word);


    /**
     * Method gets adjusted amount of ads on a page
     * @return adjusted amount of ads
     */
    List<Ad> findAllPagination();


    /**
     * Method deletes inactive ads {@link Ad#active}
     */
    void deleteInactiveAds();
}
