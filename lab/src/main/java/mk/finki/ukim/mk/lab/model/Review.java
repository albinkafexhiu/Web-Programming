package mk.finki.ukim.mk.lab.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer score;
    private String description;

    @ManyToOne
    private Book book;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    public Review(Long id, Integer score, String description, Book book, LocalDateTime timestamp) {

        this.id = id;
        this.score = score;
        this.description = description;
        this.book = book;
        this.timestamp = timestamp;
    }


}
