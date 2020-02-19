package controller;

import domain.Author;
import domain.SuitableAd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import service.AuthorService;
import java.util.List;

/**
 * Class AuthorController using to perform author CRUD operations and save, delete author suitable ads for user
 * implementation.
 * @author Polina Shcherbinina
 * @version 1.1
 */
@RestController
@RequestMapping("author")
public class AuthorController {

    /**
     * Service is object instance of {@link AuthorService} interface. It connects realization part with user application
     */
    @Autowired
    @Qualifier("authorServiceImpl")
    private AuthorService service;


    /**
     * Method saves new created author of advertisement
     * @param author new created author of advertisement {@link Author}
     */
    @PostMapping("/save")
    public void save(@RequestBody Author author) {
        service.save(author);
    }


    /**
     * Method saves suitable ad with certain parameters {@link SuitableAd}. Authors would want to be informed when
     * new ad with appropriate parameters are appeared
     * @param ad suitable advertisement
     */
    @PostMapping("/save/suitable/ad")
    public void saveSuitableAd(@RequestBody SuitableAd ad) {service.saveSuitableAd(ad);}


    /**
     * Method updates already available author
     * @param author author with id of author we want to update and new parameters for setting up {@link Author}
     */
    @PostMapping("/update")
    public void update(@RequestBody Author author) {
        service.update(author);
    }


    /**
     * Method gets author by id
     * @param id author id
     * @return author with input id
     */
    @GetMapping("/get/{id}")
    public Author get(@PathVariable int id) {
        return service.getById(id);
    }


    /**
     * Method gets sorted list of all authors by field {@link Author}
     * @param field field of author
     * @return sorted list of all authors by input field
     */
    @GetMapping("/get/all/sort/{field}")
    public List<Author> getAllSort(@PathVariable("field") String field) {
        return service.findAllSort(field);
    }


    /**
     * Method deletes author by id
     * @param id author id
     */
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }


    /**
     * Method deletes author suitable ad by id {@link SuitableAd}
     * @param id suitable advertisement id
     */
    @DeleteMapping("/delete/suitable/ad/{id}")
    public void deleteSuitableAd(@PathVariable int id) {service.deleteSuitableAd(id);}
} 
