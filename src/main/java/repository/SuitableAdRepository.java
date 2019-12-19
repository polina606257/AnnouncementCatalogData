package repository;

import domain.Category;
import domain.SuitableAd;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SuitableAdRepository extends JpaRepository<SuitableAd, Integer> {

    @Query("DELETE FROM SuitableAd sad WHERE sad.id = :id")
    @Modifying
    void deleteById(@Param("id") int id);
}
