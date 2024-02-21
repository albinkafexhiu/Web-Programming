package mk.finki.ukim.mk.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
//import mk.finki.ukim.mk.lab.repository.impl.AuthorRepository;
//import mk.finki.ukim.mk.lab.repository.impl.BookRepository;
import mk.finki.ukim.mk.lab.repository.jpa.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.jpa.BookRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ReviewRepository;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ReviewRepository reviewRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElse(null);
    }

//    @Override
//    public Book addAuthorToBook(String isbn, Long authorId) {
//        Book book = findBookByIsbn(isbn);
//        authorRepository.findById(authorId).ifPresent(author -> bookRepository.addAuthorToBook(author, book));
//        return book;
//    }

    @Transactional
    @Override
    public void addAuthorToBook(String isbn, Long authorId) {
        Book book = bookRepository.findByIsbn(isbn).orElse(null);
        Author author = authorRepository.findById(authorId).orElse(null);

        if (book != null && author != null && !book.getAuthors().contains(author)) {
            book.getAuthors().add(author);
            bookRepository.save(book);
        }
    }
    @Override
    public List<Book> searchBooks(String searchQuery) {
        return listBooks().stream().filter(a-> a.getTitle().equalsIgnoreCase(searchQuery)).toList();
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
//    @Override
//    public void save(Book book) {
//        if (book.getId() == null) {
//            book.setId(generateNewId());
//            bookRepository.add(book);
//        } else {
//            bookRepository.update(book);
//        }
//    }
    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }
    @Override
    public List<Book> findBooksByBookStoreId(Long bookStoreId) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getBookStore() != null && book.getBookStore().getId().equals(bookStoreId))
                .collect(Collectors.toList());
    }

    @Override
    public Book findById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

//    @Override
//    public void delete(Long id) {
//        bookRepository.delete(id);
//    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    private Long generateNewId() {
        long id = (long) (Math.random()*1000);
        return id;
    }
    @Override
    public List<Book> listBooksWithAvgScore() {
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            Double avgScore = reviewRepository.findAverageScoreByBookId(book.getId());
            book.setAverageScore(avgScore);
        }
        return books;
    }
    @Override
    public Book findBookWithHighestAvgScore() {
        return bookRepository.findBookWithHighestAverageScore();
    }



}
