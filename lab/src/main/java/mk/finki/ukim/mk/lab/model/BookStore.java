package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book_store")
@Data
@NoArgsConstructor
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assuming auto-generated ID
    private Long id;

    private String name;
    private String city;
    private String address;

    @OneToMany(mappedBy = "bookStore")
    private List<Book> books = new ArrayList<>();

    public BookStore(Long id, String name, String city, String address) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
    }


}
