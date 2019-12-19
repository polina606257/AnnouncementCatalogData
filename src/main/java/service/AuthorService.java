package service;

import domain.Ad;
import domain.Author;
import domain.Email;
import domain.SuitableAd;

import java.util.List;

public interface AuthorService<N> extends CrudService<Author> {

    void saveSuitableAd(SuitableAd suitableAd);

    void deleteSuitableAd(int id);



}
