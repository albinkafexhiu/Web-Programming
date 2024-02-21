package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);


    @Query("SELECT b FROM Book b JOIN Review r ON b.id = r.book.id GROUP BY b.id ORDER BY AVG(r.score) DESC")
    List<Book> findBooksOrderedByAverageScoreDesc(Pageable pageable);

    default Book findBookWithHighestAverageScore() {
        return findBooksOrderedByAverageScoreDesc(PageRequest.of(0, 1)).stream().findFirst().orElse(null);
    }


}
