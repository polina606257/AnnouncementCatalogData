package repository;

import domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * EmailRepository interface serves to simplify the data access process for Email in database, binds database to
 * implementation part
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Transactional
public interface EmailRepository extends JpaRepository<Author, Integer> {

    /**
     * Method gets author emails from database for mailing suitable advertisement when parameters new created
     * advertisement {@link domain.Ad} match to parameters of suitable ad {@link domain.SuitableAd}
     * @param name ad name
     * @param price ad price
     * @param category ad category(name)
     * @return list of emails with suitable ad where we will send advertisement
     */
    @Query( "SELECT e.email FROM SuitableAd sad " +
            "JOIN sad.author a JOIN a.email e " +
            "WHERE :name LIKE CONCAT('%', sad.text, '%') " +
            " AND (:price BETWEEN sad.priceFrom AND priceTo) AND sad.category = :category")
    List<String> findBySuitableAd(@Param("name") String name, @Param("price")BigDecimal price, @Param("category")
            String category);
}
