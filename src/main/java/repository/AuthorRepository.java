package repository;

import domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * AuthorRepository interface serves to simplify the data access process for author in database, binds database to
 * implementation part and has extended CRUD methods from JpaRepository
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Transactional
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}


