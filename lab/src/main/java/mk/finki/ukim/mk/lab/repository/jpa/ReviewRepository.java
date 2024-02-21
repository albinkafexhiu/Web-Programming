package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBookId(Long bookId);
    List<Review> findByTimestampBetween(LocalDateTime from, LocalDateTime to);
    List<Review> findByBookIdAndTimestampBetween(Long bookId, LocalDateTime from, LocalDateTime to);


    @Query("SELECT AVG(r.score) FROM Review r WHERE r.book.id = :bookId")
    Double findAverageScoreByBookId(@Param("bookId") Long bookId);
}
