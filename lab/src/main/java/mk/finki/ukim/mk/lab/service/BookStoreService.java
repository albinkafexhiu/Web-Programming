package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.BookStore;

import java.util.List;

public interface BookStoreService {
    List<BookStore> findAll();
    BookStore findById(Long id);
}
