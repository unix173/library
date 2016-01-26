package resource;

import core.Book;
import dao.BookDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by ivsi on 1/25/2016.
 */
@Path("books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    private final BookDAO bookDAO;

    public BookResource(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @POST
    @UnitOfWork
    public Long addBook(Book book) {
        return bookDAO.create(book);
    }

    @GET
    @UnitOfWork
    public List<Book> getBooks() {
        return bookDAO.findAll();
    }

}
