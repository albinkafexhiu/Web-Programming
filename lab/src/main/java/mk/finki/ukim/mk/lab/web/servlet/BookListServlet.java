package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookListServlet", urlPatterns = {"/listbooks"})
public class BookListServlet extends HttpServlet {
    private final BookService bookService;
    private final SpringTemplateEngine templateEngine;

    public BookListServlet(BookService bookService, SpringTemplateEngine templateEngine) {
        this.bookService = bookService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchQuery = req.getParameter("searchQuery");
        List<Book> books;
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            books = bookService.searchBooks(searchQuery);
        } else {
            books = bookService.listBooks();
        }

        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        context.setVariable("books", books);
        templateEngine.process("listBooks.html", context, resp.getWriter());

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String isbn = req.getParameter("bookIsbn");

        if (isbn != null && !isbn.trim().isEmpty()) {
            req.setAttribute("selectedBookIsbn", isbn);

            req.getRequestDispatcher("/authors").forward(req, resp);
        } else {
            req.setAttribute("error", "No book selected. Please choose a book.");
            doGet(req, resp);
        }
    }
}