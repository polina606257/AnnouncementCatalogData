package repository;

import domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    //    @EntityGraph(attributePaths = "ads")
//    Category getById(int id);
//
//    @Query("DELETE FROM Category c WHERE c.id = :id")
//    @Modifying
//    void deleteById(@Param("id") int id);

}
