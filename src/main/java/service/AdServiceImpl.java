package service;

import domain.Ad;
import domain.Author;
import domain.Category;
import domain.SuitableAd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import repository.AdRepository;
import java.time.LocalDate;
import java.util.List;

@Service
public class AdServiceImpl implements AdService<Ad> {

    @Autowired
    private AdRepository repository;

    @Autowired
    private EmailService emailService;

    public AdServiceImpl() {
    }

    @Override
    public void save(Ad ad) {
        repository.save(ad);
       // emailService.sendEmailWithSuitableAd(ad);
    }

    @Override
    public void update(Ad ad) {
        repository.save(ad);
    }

    @Override
    public Ad getById(int id) {
        return repository.findById(id).get();
    }


    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }


    @Override
    public List<Ad> findAllSort(String field) {
        Sort sort = Sort.by(Sort.Order.asc(field));
        return repository.findAll(sort);
    }


    @Override
    public List<Ad> getByCategory(int id) {
        List<Ad> adsList = repository.findAllByCategory_Id(id);
        return adsList;
    }


    @Override
    public List<Ad> getByCategories(List<Integer> ids) {
        List<Ad> adsList = repository.findAllByCategoryIsIn(ids);
        return adsList;
    }


    @Override
    public List<Ad> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Ad> getByAuthor(int id) {
        List<Ad> adsList = repository.findAllByAuthor_Id(id);
        return adsList;
    }


    @Override
    public List<Ad> getByDate(LocalDate date) {
        List<Ad> adsList = repository.findAllByDate(date);
        return adsList;
    }


    @Override
    public List<Ad> getByKeyWord(String word) {
        List<Ad> adsList = repository.findAllByNameContaining(word);
        return adsList;
    }


    public List<Ad> findAllPagination() {
        PageRequest pageRequest = PageRequest.of(1, 3);
        Page<Ad> ads = repository.findAll(pageRequest);
        return ads.getContent();
    }


//    @Override
//    @Scheduled(fixedDelay = 5000)
//    public void deleteInactiveAds() {
//        System.out.println("I'm here");
//    }

}
