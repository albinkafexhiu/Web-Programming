package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.jpa.BookStoreRepository;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreServiceImpl implements BookStoreService {
    private final BookStoreRepository bookStoreRepository;

    public BookStoreServiceImpl(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }


    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }

//    @Override
//    public BookStore findById(Long id) {
//        return bookStoreRepository.findById(id);
//    }
    @Override
    public BookStore findById(Long id) {
        return bookStoreRepository.findById(id).orElse(null);
    }

}
