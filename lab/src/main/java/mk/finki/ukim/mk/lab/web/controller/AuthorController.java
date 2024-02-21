package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping
    public String showAuthors(@RequestParam("bookIsbn") String bookIsbn, Model model) {


        Book book = bookService.findBookByIsbn(bookIsbn);
        if (book == null) {

            model.addAttribute("error", "Book not found for the provided ISBN.");
            return "errorPage";
        }

        model.addAttribute("selectedBook", book);
        model.addAttribute("authors", authorService.listAuthors());
        return "authorList";
    }
    @PostMapping("/addAuthorToBook")
    public String addAuthorToBook(@RequestParam Long authorId,
                                  @RequestParam String bookIsbn,
                                  RedirectAttributes redirectAttributes) {
        Optional<Author> author = authorService.findById(authorId);
        Book book = bookService.findBookByIsbn(bookIsbn);

        if (author != null && book != null) {
            bookService.addAuthorToBook(bookIsbn, authorId);
            return "redirect:/bookDetails?isbn=" + bookIsbn;
        } else {
            redirectAttributes.addFlashAttribute("error", "Author or book not found.");
            return "redirect:/authors/" + bookIsbn;
        }
    }


}
