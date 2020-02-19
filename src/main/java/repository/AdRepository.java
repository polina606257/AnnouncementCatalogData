package repository;

import domain.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * AdRepository interface serves to simplify the data access process for ad in database, binds database to implementation
 * part, has extended CRUD methods from JpaRepository and queries for getting need additional data from database
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Transactional
public interface AdRepository extends JpaRepository<Ad, Integer> {

    /**
     * Method gets all advertisements by category id from database
     * @param id category id
     * @return list advertisements related to this category
     */
    List<Ad> findAllByCategory_Id(int id);


    /**
     * Method gets all advertisements by ids of several categories from database
     * @param ids category ids
     * @return list advertisements related to all those categories
     */
    @Query("SELECT ad FROM Ad ad WHERE ad.category.id IN :ids")
    List<Ad> findAllByCategoryIsIn(@Param("ids") List<Integer> ids);


    /**
     * Method gets advertisements by author id from database
     * @param id author id
     * @return list of advertisements related to this author
     */
    List<Ad> findAllByAuthor_Id(int id);


    /**
     * Method gets advertisements by date of creation from database
     * @param date ad date of creation
     * @return list of advertisements where creation date is input date
     */
    @Query("SELECT ad FROM Ad ad WHERE ad.localDate = :date")
    List<Ad> findAllByDate(@Param ("date")LocalDate date);


    /**
     * Method gets ads from database if their name {@link Ad#name} contains input word
     * @param word word that ad name has to contain
     * @return list of ads that contains input word
     */
    List<Ad> findAllByNameContaining(String word);


    /**
     * Methods deletes advertisement from database by its id
     * @param id ad id
     */
    @Query("DELETE FROM Ad ad WHERE ad.id = :id")
    @Modifying
    void deleteById(@Param("id") int id);


    /**
     * Method deletes advertisements from database when they marked as inactive
     */
    @Query("DELETE FROM Ad ad WHERE ad.active = false")
    @Modifying
    void deleteInactiveAds();
}
