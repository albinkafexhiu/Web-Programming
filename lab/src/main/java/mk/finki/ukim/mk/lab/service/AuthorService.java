package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> listAuthors();
    Optional<Author> findById(Long id);
}