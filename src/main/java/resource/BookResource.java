package resource;

import core.Rentee;
import core.book.Book;
import dao.BookDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import operations.book.*;

import javax.annotation.security.PermitAll;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by ivsi on 1/25/2016.
 */
@Path("books")
public class BookResource {

    private final BookDAO bookDAO;

    public BookResource(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @PermitAll
    @POST
    @UnitOfWork
    public Book addBook(Book book) {
        return new AddNewBook(bookDAO).execute(book);
    }

    @PermitAll
    @GET
    @UnitOfWork
    public List<Book> getBooks() {
        return new GetAllBooks(bookDAO).execute();
    }


    @PermitAll
    @GET
    @Path("/available")
    @UnitOfWork
    public List<Book> getAvailableBooks() {
        return new GetAvailableBooks(bookDAO).execute();
    }

    @PermitAll
     @PUT
     @Path("{bookId}")
     @UnitOfWork
     public Book updateBook(@PathParam("bookId") Long bookId, Book book) {
        return new UpdateBook(bookDAO).execute(bookId, book);
    }

    @PermitAll
    @DELETE
    @Path("{bookId}")
    @UnitOfWork
    public Book deleteBook(@PathParam("bookId") Long bookId) {
        return new DeleteBook(bookDAO).execute(bookId);
    }


}
