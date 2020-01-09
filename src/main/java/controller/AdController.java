package controller;

import domain.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.AdService;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

/**
 * Class AdController using to perform advertisement(ad) CRUD methods for user implementation
 * @author Polina Shcherbinina
 * @version 1.1
 */
@RestController
@RequestMapping("ad")
public class AdController {

    /**
     * Service is object instance of {@link AdService} interface. It connects realization part with user application
     */
    @Autowired
    private AdService<Ad> service;


    /**
     * Method saves new created ad
     * @param ad new user created advertisement {@link Ad}
     */
    @PostMapping("/save")
    public void save(@RequestBody Ad ad) {
        service.save(ad);
    }


    /**
     * Method updates already available ad
     * @param ad advertisement with id of ad we want to update and new parameters for setting up {@link Ad}
     */
    @PostMapping("/update")
    public void update(@RequestBody Ad ad) {
        service.update(ad);
    }


    /**
     * Method gets ad by id
     * @param id advertisement id
     * @return ad with input id
     */
    @GetMapping("/get/{id}")
    public Ad get(@PathVariable int id) {
        return service.getById(id);
    }


    /**
     * Method deletes ad by id
     * @param id advertisement id
     */
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }


    /**
     * Method gets list of ads by category id  {@link domain.Category}
     * @param id category id
     * @return advertisements with input category id
     */
    @GetMapping("/get/by/category/{id}")
    public List<Ad> getByCategory(@PathVariable int id) {
        return service.getByCategory(id);
    }


    /**
     * Method gets list of ads by several category ids {@link domain.Category}
     * @param ids - ids of categories
     * @return advertisements with input category ids
     */
    @GetMapping("/get/by/categories/{ids}")
    public List<Ad> getByCategories(@PathVariable List<Integer> ids) {
        return service.getByCategories(ids);
    }


    /**
     * Method gets all ads
     * @return all advertisements database contains
     */
    @GetMapping("/list")
    public List<Ad> getAll() {
        return service.getAll();
    }


    /**
     * Method getsg sorted list of all ads by field {@link Ad}
     * @param field - field of advertisement
     * @return sorted list of all advertisements by input field
     */
    @GetMapping("/sort/list/{field}")
    public List<Ad> getAllSort(@PathVariable String field) {return service.findAllSort(field);}


    /**
     * Method gets list of ads by author id {@link domain.Author}
     * @param id author id
     * @return advertisements with input author id
     */
    @GetMapping("/by/author/{id}")
    public List<Ad> getByAuthor(@PathVariable int id) {
        return service.getByAuthor(id);
    }


    /**
     * Method gets list of ads by date {@link Ad#date}
     * @param date - date of advertisement creation
     * @return advertisements created in input date
     */
    @GetMapping("/get/by/date/{date}")
    public List<Ad> getByDate(@PathVariable(name = "date") long date) {
        return service.getByDate(Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate());}


    /**
     * Method gets list of ads by word contained in name of advertisement {@link Ad#name}
     * @param word word that name of advertisement has to contain
     * @return advertisements containing in their name input word
     */
    @GetMapping("/get/by/key/word/{word}")
    public List<Ad> getByKeyWord(@PathVariable String word) {
        return service.getByKeyWord(word);
    }


    /**
     * Method gets adjusted amount of ads on a page
     * @return adjusted amount of advertisements on a page
     */
    @GetMapping("/get/page")
    public List<Ad> getPage() {
        return service.findAllPagination();
    }
}
