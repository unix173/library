package resource;

import core.Book;
import core.BookReview;
import core.Rentee;
import dao.BookReviewDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by ivsi on 1/25/2016.
 */
@Path("books")
public class BookReviewResource {

    private final BookReviewDAO bookReviewDAO;

    public BookReviewResource(BookReviewDAO bookReviewDAO) {
        this.bookReviewDAO = bookReviewDAO;
    }

    @Path("{bookId}/bookReview/rentees/{renteeId}")
    @UnitOfWork
    @POST
    public BookReview addBookReview(@PathParam("bookId") Long bookId, @PathParam("renteeId") Long renteeId, BookReview bookReview) {
        Book book = new Book();
        book.setBookId(bookId);
        bookReview.setBook(book);
        Rentee rentee = new Rentee();
        rentee.setRenteeId(renteeId);
        bookReview.setRentee(rentee);
        return bookReviewDAO.create(bookReview);
    }

    @GET
    @Path("{bookId}/bookReview")
    @UnitOfWork
    public List<BookReview> getBookReviews(@PathParam("bookId") Long bookId) {
        return bookReviewDAO.findByBookId(bookId);
    }

}
