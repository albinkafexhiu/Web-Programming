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
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "AuthorServlet", urlPatterns = {"/authors"})
public class AuthorServlet extends HttpServlet {
    private final AuthorService authorService;
    private final BookService bookService;
    private final SpringTemplateEngine templateEngine;

    public AuthorServlet(AuthorService authorService, BookService bookService, SpringTemplateEngine templateEngine) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.templateEngine = templateEngine;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedBookIsbn = req.getParameter("bookIsbn");

        if (selectedBookIsbn == null || selectedBookIsbn.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Book ISBN is missing.");
            return;
        }

        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        context.setVariable("selectedBookIsbn", selectedBookIsbn);
        context.setVariable("authors", authorService.listAuthors());

        templateEngine.process("authorList.html", context, resp.getWriter());
    }

}
