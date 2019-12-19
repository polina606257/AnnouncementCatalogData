package controller;

import domain.Ad;
import domain.Author;
import domain.Email;
import domain.SuitableAd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import service.AuthorService;
import service.CrudService;
import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {
    @Autowired
    @Qualifier("authorServiceImpl")
    private AuthorService<Author> service;

    @PostMapping("/save")
    public void save(@RequestBody Author author) {
        service.save(author);
    }

    @PostMapping("/save/suitable/ad")
    public void saveSuitableAd(@RequestBody SuitableAd ad) {service.saveSuitableAd(ad);}

    @PostMapping("/update")
    public void update(@RequestBody Author author) {
        service.update(author);
    }

    @GetMapping("/get/{id}")
    public Author get(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping("/get/all/sort/{field}")
    public List<Author> getAllSort(@PathVariable("field") String field) {
        return service.findAllSort(field);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @DeleteMapping("/delete/suitable/ad/{id}")
    public void deleteSuitableAd(@PathVariable int id) {service.deleteSuitableAd(id);}

} 
