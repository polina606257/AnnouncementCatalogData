package repository;

import domain.Ad;
import domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("SELECT e.email FROM Email e JOIN Author a JOIN SuitableAd sad WHERE sad.category = ad.category AND sad.text" +
            " LIKE '%ad.name%' AND sad.priceFrom <= ad.price AND sad.priceTo >= ad.price")
    List<String> findBySuitableAd(Ad ad);


}


