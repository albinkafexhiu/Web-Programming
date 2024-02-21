//package mk.finki.ukim.mk.lab.repository.impl;
//
//import mk.finki.ukim.mk.lab.model.BookStore;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class BookStoreRepository {
//    private final List<BookStore> bookStores = new ArrayList<>();
//
//
//    public BookStoreRepository() {
//        bookStores.add(new BookStore(1L, "Niku", "Kicevo", "Krste Misirkov 10"));
//        bookStores.add(new BookStore(2L, "Feniks", "Skopje", "Mislesevo 6250 2"));
//        bookStores.add(new BookStore(3L, "Brajca Miladinovci", "Tetovo", "11 Shtatori"));
//        bookStores.add(new BookStore(4L, "Akademska Kniga", "Ohrid", "Bulevard 22"));
//        bookStores.add(new BookStore(5L, "Gjon Buzuku", "Struga", "Rruga B"));
//    }
//
//    public List<BookStore> findAll() {
//        return new ArrayList<>(bookStores);
//    }
//
//    public BookStore findById(Long id) {
//        return bookStores.stream()
//                .filter(bookStore -> bookStore.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
//
//}
