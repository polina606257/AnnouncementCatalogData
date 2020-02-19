package service;

import domain.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import repository.AdRepository;
import java.time.LocalDate;
import java.util.List;

/**
 * Class AdServiceImpl implements all methods from {@link AdService}
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Service
public class AdServiceImpl implements AdService {

    /**
     * Repository is an instance object of {@link AdRepository} interface. it has all CRUD methods for advertisement
     */
    @Autowired
    private AdRepository repository;

    /**
     * EmailService is an instance object of {@link EmailService} interface
     */
    @Autowired
    private EmailService emailService;


    /**
     * Default constructor. Create AdService object without parameters
     */
    public AdServiceImpl() {
    }


    /**
     * Method saves new created advertisement and sent it to author emails with suitable ads
     * @param ad new created advertisement
     */
    @Override
    public void save(Ad ad) {
        repository.save(ad);
        emailService.sendEmailWithSuitableAd(ad);
    }


    /**
     * Method saves new created advertisement and sent it to author emails with suitable ads
     * @param ad new created advertisement
     */
    @Override
    public void update(Ad ad) {
        repository.save(ad);
    }


    /**
     * Method gets ad by id
     * @param id ad id
     * @return ad with input id
     */
    @Override
    public Ad getById(int id) {
        return repository.findById(id).get();
    }


    /**
     * Method deletes ad by id
     * @param id ad id
     */
    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }


    /**
     * Method gets sorted list of all ads by field {@link Ad}
     * @param field field of ad
     * @return sorted list of all ads by input field
     */
    @Override
    public List<Ad> findAllSort(String field) {
        Sort sort = Sort.by(Sort.Order.asc(field));
        return repository.findAll(sort);
    }


    /**
     * Method gets list of ads by category id  {@link domain.Category}
     * @param id id of category
     * @return advertisements with input category id
     */
    @Override
    public List<Ad> getByCategory(int id) {
        List<Ad> adsList = repository.findAllByCategory_Id(id);
        return adsList;
    }


    /**
     * Method gets list of ads by category ids  {@link domain.Category}
     * @param ids ids of several categories
     * @return advertisements with input ids of categories
     */
    @Override
    public List<Ad> getByCategories(List<Integer> ids) {
        List<Ad> adsList = repository.findAllByCategoryIsIn(ids);
        return adsList;
    }


    /**
     * Method gets all ads
     * @return all advertisements database contains
     */
    @Override
    public List<Ad> getAll() {
        return repository.findAll();
    }


    /**
     * Method gets list of ads by author id {@link domain.Author}
     * @param id author id
     * @return advertisements with input author id
     */
    @Override
    public List<Ad> getByAuthor(int id) {
        List<Ad> adsList = repository.findAllByAuthor_Id(id);
        return adsList;
    }


    /**
     * Method gets list of ads by date {@link Ad#date}
     * @param date date of advertisement creation
     * @return advertisements created in input date
     */
    @Override
    public List<Ad> getByDate(LocalDate date) {
        List<Ad> adsList = repository.findAllByDate(date);
        return adsList;
    }


    /**
     * Method gets list of ads by word contained in name of advertisement {@link Ad#name}
     * @param word word that name of advertisement has to contain
     * @return advertisements containing in their name input word
     */
    @Override
    public List<Ad> getByKeyWord(String word) {
        return repository.findAllByNameContaining(word);
    }


    /**
     * Method gets adjusted amount of ads on a page
     * @return adjusted amount of advertisements on a page
     */
    @Override
    public List<Ad> findAllPagination() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<Ad> ads = repository.findAll(pageRequest);
        return ads.getContent();
    }

    /**
     * Method deletes inactive ads every day at 11 pm
     */
    @Override
    @Scheduled(cron = "0 39 17 * * ?")
    public void deleteInactiveAds() {
        repository.deleteInactiveAds();
    }
}
