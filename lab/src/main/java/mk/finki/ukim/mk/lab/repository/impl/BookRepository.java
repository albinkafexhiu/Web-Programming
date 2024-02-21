//package mk.finki.ukim.mk.lab.repository.impl;
//
//import mk.finki.ukim.mk.lab.model.Author;
//import mk.finki.ukim.mk.lab.model.Book;
//import mk.finki.ukim.mk.lab.model.BookStore;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class BookRepository {
//    private List<Book> books = new ArrayList<>();
//    private final BookStoreRepository bookStoreRepository;
//
//    public BookRepository(BookStoreRepository bookStoreRepository) {
//        this.bookStoreRepository = bookStoreRepository;
//        List<BookStore> availableBookStores = bookStoreRepository.findAll();
//        BookStore defaultBookStore = availableBookStores.get(0);
//
//        books.add(new Book("1234567890", "Fjalet e Qiririt", "Thriller", 2001, defaultBookStore));
//        books.add(new Book("2345678901", "Meshari", "Comedy", 2022, defaultBookStore));
//        books.add(new Book("3456789012", "Hamlet", "Action", 2021, defaultBookStore));
//        books.add(new Book("4567890123", "Macbeth", "Horror", 2020, defaultBookStore));
//        books.add(new Book("5678901234", "Romeo and Juliet", "Romance", 2019, defaultBookStore));
//    }
//
//    public List<Book> findAll() {
//        return books;
//    }
//
//    public Optional<Book> findByIsbn(String isbn) {
//        return books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst();
//    }
//    public Optional<Book> findById(Long id){
//        return books.stream().filter(book->book.getId().equals(id)).findFirst();
//    }
//
//    public void addAuthorToBook(Author author, Book book) {
//        if (author != null && book != null && !book.getAuthors().contains(author)) {
//            book.getAuthors().add(author);
//        }
//    }
//
//    public void update(Book book) {
//        books = books.stream()
//                .map(b -> b.getId().equals(book.getId()) ? book : b)
//                .collect(Collectors.toList());
//    }
//
//    public void delete(Long id) {
//        books.removeIf(book -> book.getId().equals(id));
//    }
//    public void add(Book book) {
//
//        books.add(book);
//
//    }
//}