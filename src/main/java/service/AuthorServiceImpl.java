package service;

import domain.Author;
import domain.SuitableAd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.AuthorRepository;
import repository.SuitableAdRepository;
import java.util.List;

/**
 * Class AuthorServiceImpl implements all methods from {@link AuthorService}
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    /**
     * Field authorRepository is an object of {@link AuthorRepository}, it has all CRUD methods for Author
     */
    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Field suitableAdRepository is an object of {@link SuitableAdRepository}, it has all CRUD methods for SuitableAd
     */
    @Autowired
    private SuitableAdRepository suitableAdRepository;


    /**
     * Default constructor. Creates AuthorServiceImpl object without parameters
     */
    public AuthorServiceImpl() {
    }


    /**
     * Method save new author to database
     * @param author author of advertisement
     */
    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }


    /**
     * Method updates already available author
     * @param author  author with id of author we want to update and new parameters for setting up {@link Author}
     */
    @Override
    public void update(Author author) {
        authorRepository.save(author);
    }


    /**
     * Method gets author by id
     * @param id author id
     * @return author with input id
     */
    @Override
    public Author getById(int id) {
        return authorRepository.findById(id).get();
    }


    /**
     * Method deletes author by id
     * @param id author id
     */
    @Override
    public void deleteById(int id) {
        authorRepository.deleteById(id);
    }


    /**
     * Method gets sorted list of all authors by field {@link Author}
     * @param field field of author
     * @return sorted list of all authors by input field
     */
    @Override
    public List<Author> findAllSort(String field) {
        Sort sort = Sort.by(Sort.Order.asc(field));
        return authorRepository.findAll(sort);
    }


    /**
     * Method saves suitable ad with certain parameters {@link SuitableAd}. Authors would want to be informed when
     * new ad with appropriate parameters are appeared
     * @param suitableAd suitable advertisement
     */
    @Override
    public void saveSuitableAd(SuitableAd suitableAd) {
       suitableAdRepository.save(suitableAd);
    }


    /**
     * Method deletes suitable ad by id {@link SuitableAd}
     * @param id suitable advertisement id
     */
    @Override
    public void deleteSuitableAd(int id) {
        suitableAdRepository.deleteById(id);
    }
}
