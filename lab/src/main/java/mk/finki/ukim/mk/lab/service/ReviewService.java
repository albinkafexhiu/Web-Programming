package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Review;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewService {
    List<Review> findReviewsByBookId(Long bookId);

    List<Review> findReviewsByBookIdAndDateRange(Long bookId, LocalDateTime from, LocalDateTime to);

    List<Review> findReviewsBetweenDates(LocalDateTime from, LocalDateTime to);

    void saveReview(Review newReview);
}
