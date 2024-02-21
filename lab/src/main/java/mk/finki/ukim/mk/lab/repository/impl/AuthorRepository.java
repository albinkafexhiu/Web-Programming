//package mk.finki.ukim.mk.lab.repository.impl;
//
//import mk.finki.ukim.mk.lab.model.Author;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//// AuthorRepository.java
//@Repository
//public class AuthorRepository {
//    private List<Author> authorList = new ArrayList<>();
//
////    public AuthorRepository() {
//////        authorList.add(new Author(13L,12-12-2002, "William ", "Shakespear", "authbio1"));
////        authorList.add(new Author(14L, "Agatha ", "Christie", "authbio2"));
////        authorList.add(new Author(15L, "Barbara ", "Cartland", "authbio3"));
////        authorList.add(new Author(16L, "Danielle ", "Steel", "authbio4"));
////        authorList.add(new Author(17L, "Naim ", "Frasheri", "authbio5"));
////    }
//
//    public List<Author> findAll() {
//        return new ArrayList<>(authorList);
//    }
//
//    public Optional<Author> findById(Long id) {
//        return authorList.stream().filter(author -> author.getId().equals(id)).findFirst();
//    }
//
//}
