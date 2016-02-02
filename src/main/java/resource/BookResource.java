package resource;

import core.book.Book;
import dao.BookDAO;
import io.dropwizard.hibernate.UnitOfWork;
import operations.book.*;

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

    @POST
    @UnitOfWork
    public Book addBook(Book book) {
        return new AddNewBook(bookDAO).execute(book);
    }

    @GET
    @UnitOfWork
    public List<Book> getBooks() {
        return new GetAllBooks(bookDAO).execute();
    }

    @GET
    @Path("/available")
    @UnitOfWork
    public List<Book> getAvailableBooks() {
        return new GetAvailableBooks(bookDAO).execute();
    }

    @PUT
    @Path("{bookId}")
    @UnitOfWork
    public Book updateBook(@PathParam("bookId") Long bookId, Book book) {
        return new UpdateBook(bookDAO).execute(bookId, book);
    }

    @DELETE
    @Path("{bookId}")
    @UnitOfWork
    public Book deleteBook(@PathParam("bookId") Long bookId) {
        return new DeleteBook(bookDAO).execute(bookId);
    }


}
