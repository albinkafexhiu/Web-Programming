package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.repository.jpa.ReviewRepository;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findReviewsByBookId(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    @Override
    public List<Review> findReviewsBetweenDates(LocalDateTime from, LocalDateTime to) {
        return reviewRepository.findByTimestampBetween(from, to);
    }
    @Override
    public List<Review> findReviewsByBookIdAndDateRange(Long bookId, LocalDateTime from, LocalDateTime to) {
        // Implement the logic to fetch reviews for the specific book within the date range
        // This could involve calling a custom method in the ReviewRepository
        return reviewRepository.findByBookIdAndTimestampBetween(bookId, from, to);

    }

    @Override
    public void saveReview(Review review) {
        reviewRepository.save(review);

    }

}
