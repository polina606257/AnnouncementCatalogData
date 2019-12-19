package controller;

import domain.Ad;
import domain.Author;
import domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import service.AdService;

import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("ad")
public class AdController {

    @Autowired
    private AdService<Ad> service;

    @PostMapping("/save")
    public void save(@RequestBody Ad ad) {
        service.save(ad);
    }

    @PostMapping("/update")
    public void update(@RequestBody Ad ad) {
        service.update(ad);
    }

    @GetMapping("/get/{id}")
    public Ad get(@PathVariable int id) {
        return service.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @GetMapping("/get/by/category/{id}")
    public List<Ad> getByCategory(@PathVariable int id) {
        return service.getByCategory(id);
    }

    @GetMapping("/get/by/categories/{ids}")
    public List<Ad> getByCategories(@PathVariable List<Integer> ids) {
        return service.getByCategories(ids);
    }

    @GetMapping("/list")
    public List<Ad> getAll() {
        return service.getAll();
    }

    @GetMapping("/sort/list/{word}")
    public List<Ad> getAllSort(@PathVariable String word) {return service.findAllSort(word);}

    @GetMapping("/ /by/author/{id}")
    public List<Ad> getByAuthor(@PathVariable int id) {
        return service.getByAuthor(id);
    }

    @GetMapping("/get/by/date/{date}")
    public List<Ad> getByDate(@PathVariable(name = "date") long date) {
        return service.getByDate(Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate());
    }

    @GetMapping("/get/by/key/word/{word}")
    public List<Ad> getByKeyWord(@PathVariable String word) {
        return service.getByKeyWord(word);
    }

    @GetMapping("/get/page")
    public List<Ad> getPage() {
        return service.findAllPagination();
    }
}
