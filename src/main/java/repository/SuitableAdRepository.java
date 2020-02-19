package repository;

import domain.SuitableAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * SuitableAdRepository interface serves to simplify the data access process for Suitable ad in database, binds database
 * to implementation part has extended CRUD methods from JPARepository
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Transactional
public interface SuitableAdRepository extends JpaRepository<SuitableAd, Integer> {

    /**
     * Method deletes suitable ad by id
     * @param id suitable ad id
     */
    @Query("DELETE FROM SuitableAd sad WHERE sad.id = :id")
    @Modifying
    void deleteById(@Param("id") int id);


    /**
     * Method gets SuitableAd by id. Method is used just for tests
     * @param id suitableAd id
     * @return suitableAd by id
     */
    SuitableAd findById(int id);
}
