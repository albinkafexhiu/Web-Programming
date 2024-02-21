package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final BookService bookService;

    @Autowired
    public ReviewController(ReviewService reviewService, BookService bookService) {
        this.reviewService = reviewService;
        this.bookService = bookService;
    }

    @GetMapping("/books/{bookId}/reviews")
    public String showReviewsForBook(@PathVariable Long bookId, Model model) {
        List<Review> reviews = reviewService.findReviewsByBookId(bookId);
        model.addAttribute("reviews", reviews);
        model.addAttribute("selectedBookId", bookId);

        return "reviews";
    }
    @GetMapping("/reviews/filter")
    public String filterReviews(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
                                @RequestParam Long bookId, // Added parameter for bookId
                                Model model) {
        List<Review> filteredReviews = reviewService.findReviewsByBookIdAndDateRange(bookId, from.atStartOfDay(), to.atTime(23, 59, 59));
        model.addAttribute("reviews", filteredReviews);
        model.addAttribute("selectedBookId", bookId); // Pass the bookId back to the view
        return "reviews";
    }
    @PostMapping("/submitReview")
    public String submitReview(@RequestParam Long bookId, @RequestParam Integer score, @RequestParam String description,
                               @RequestParam("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp,
                               RedirectAttributes redirectAttributes) {
        Book book = bookService.findById(bookId);
        if (book == null) {
            redirectAttributes.addFlashAttribute("error", "Book not found.");
            return "redirect:/books";
        }

        Review newReview = new Review(null, score, description, book, timestamp);
        reviewService.saveReview(newReview);
        redirectAttributes.addFlashAttribute("message", "Review added successfully!");
        return "redirect:/books/" + bookId + "/reviews";
    }




}

