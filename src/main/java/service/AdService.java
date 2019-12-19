package service;

import domain.Ad;
import domain.Author;
import domain.Category;
import domain.SuitableAd;

import java.time.LocalDate;
import java.util.List;

public interface AdService<M> extends CrudService<Ad> {

    List<Ad> getByCategory(int id);

    List<Ad> getByCategories(List<Integer> ids);

    List<Ad> getAll();

    List<Ad> findAllSort(String field);

    List<Ad> getByAuthor(int id);

    List<Ad> getByDate(LocalDate date);

    List<Ad> getByKeyWord(String word);

    List<Ad> findAllPagination();

  //  void deleteInactiveAds();
}
