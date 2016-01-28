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
public class BookResource {

    private final BookDAO bookDAO;

    public BookResource(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @POST
    @UnitOfWork
    public Book addBook(Book book) {
        return bookDAO.create(book);
    }

    @GET
    @UnitOfWork
    public List<Book> getBooks() {
        return bookDAO.findAll();
    }

    @PUT
    @Path("{bookId}")
    @UnitOfWork
    public Book updateBook(@PathParam("bookId") Long bookId, Book book) {
        Book bookToUpdate = bookDAO.findById(bookId);
        bookToUpdate.setTitle(book.getTitle());
        return bookDAO.update(bookToUpdate);
    }

    @DELETE
    @Path("{bookId}")
    @UnitOfWork
    public Book deleteBook(@PathParam("bookId") Long bookId) {
        Book bookToDelete = bookDAO.findById(bookId);
        return bookDAO.delete(bookToDelete);
    }

}
