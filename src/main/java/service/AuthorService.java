package service;

import domain.Author;
import domain.SuitableAd;

/**
 * AuthorService interface binds realization part with user, contains methods extended from {@link CrudService} for
 * author type and methods for author suitable ad
 * @author Polina Shcherbinina
 * @version 1.1
 */
public interface AuthorService extends CrudService<Author> {

    /**
     * Method saves suitable ad
     * @param suitableAd input suitable ad for saving
     */
    void saveSuitableAd(SuitableAd suitableAd);


    /**
     * Method deletes suitable ad
     * @param id suitable ad id
     */
    void deleteSuitableAd(int id);
}
