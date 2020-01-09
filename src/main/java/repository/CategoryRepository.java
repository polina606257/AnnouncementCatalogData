package repository;

import domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * CategoryRepository interface serves to simplify the data access process for category in database, binds database to
 * implementation part and has extended CRUD methods from JpaRepository
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
