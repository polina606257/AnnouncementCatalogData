package repository;

import domain.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
public interface AdRepository extends JpaRepository<Ad, Integer> {

    List<Ad> findAllByCategory_Id(int id);

    @Query("SELECT ad FROM Ad ad WHERE ad.category.id IN :ids")
    List<Ad> findAllByCategoryIsIn(@Param("ids") List<Integer> ids);

    List<Ad> findAllByAuthor_Id(int id);

    @Query("SELECT ad FROM Ad ad WHERE ad.date = :date")
    List<Ad> findAllByDate(@Param ("date")LocalDate date);

    List<Ad> findAllByNameContaining(String word);

    @Query("DELETE FROM Ad ad WHERE ad.id = :id")
    @Modifying
    void deleteById(@Param("id") int id);
}
