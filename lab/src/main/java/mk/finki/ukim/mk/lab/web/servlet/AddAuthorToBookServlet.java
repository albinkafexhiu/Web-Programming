package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "AddAuthorToBookServlet", urlPatterns = {"/addAuthorToBook"})
public class AddAuthorToBookServlet extends HttpServlet {
    private final AuthorService authorService;
    private final BookService bookService;

    // Constructor
    public AddAuthorToBookServlet(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authorIdStr = req.getParameter("authorId");
        String bookIsbn = req.getParameter("selectedBookIsbn");

        String errorMessage = null;

        Long authorId = null;
        try {
            authorId = Long.parseLong(authorIdStr);
        } catch (NumberFormatException e) {
            errorMessage = "Invalid author ID format.";
        }

        if (authorId != null && bookIsbn != null) {
            Optional<Author> author = authorService.findById(authorId);
            Book book = bookService.findBookByIsbn(bookIsbn);

            if (author.isPresent() && book != null) {
                bookService.addAuthorToBook(bookIsbn, authorId);

                resp.sendRedirect(req.getContextPath() + "/bookDetails?isbn=" + bookIsbn);
                return;
            } else {
                errorMessage = "Author or book not found.";
            }
        } else {
            if (errorMessage == null) {
                errorMessage = "Author ID or Book ISBN missing.";
            }
        }

        req.setAttribute("error", errorMessage);
        req.setAttribute("selectedBookIsbn", bookIsbn);
        req.getRequestDispatcher("/authors").forward(req, resp);
    }

}