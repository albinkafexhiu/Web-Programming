package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listBooks();
    Book findBookByIsbn(String isbn);
    void addAuthorToBook(String isbn, Long authorId);

    List<Book> searchBooks(String searchQuery);
    List<Book> findAll();
    void save(Book book);


    Book findById(Long bookId);

    void delete(Long id);

    List<Book> findBooksByBookStoreId(Long bookStoreId);

    List<Book> listBooksWithAvgScore();

    Book findBookWithHighestAvgScore();
}