package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookStoreController {

    private final BookStoreService bookStoreService;
    private final BookService bookService;

    @Autowired
    public BookStoreController(BookStoreService bookStoreService, BookService bookService) {
        this.bookStoreService = bookStoreService;
        this.bookService = bookService;
    }

    @GetMapping("/bookstores")
    public String getAllBookStores(Model model) {
        model.addAttribute("bookStores", bookStoreService.findAll());
        return "bookstores";
    }

    @GetMapping("/bookstores/{id}")
    public String getBooksByBookStore(@PathVariable Long id, Model model) {
        model.addAttribute("books", bookService.findBooksByBookStoreId(id));
        model.addAttribute("bookStore", bookStoreService.findById(id));
        return "booksInBookStore";
    }
}

