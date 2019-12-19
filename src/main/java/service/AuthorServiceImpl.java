package service;

import domain.Ad;
import domain.Author;
import domain.Email;
import domain.SuitableAd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.AuthorRepository;
import repository.SuitableAdRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService<Author> {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private SuitableAdRepository suitableAdRepository;

    public AuthorServiceImpl() {
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void update(Author author) {
        authorRepository.save(author);
    }

    @Override
    public Author getById(int id) {
        return authorRepository.findById(id).get();
    }


    @Override
    public void deleteById(int id) {
        authorRepository.deleteById(id);
    }


    @Override
    public List<Author> findAllSort(String field) {
        Sort sort = Sort.by(Sort.Order.asc(field));
        return authorRepository.findAll(sort);
    }

    @Override
    public void saveSuitableAd(SuitableAd suitableAd) {
       suitableAdRepository.save(suitableAd);
    }

    @Override
    public void deleteSuitableAd(int id) {
        suitableAdRepository.deleteById(id);
    }


}
