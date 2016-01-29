package resource;

import core.BookReview;
import dao.BookDAO;
import dao.BookReviewDAO;
import dao.RenteeDAO;
import io.dropwizard.hibernate.UnitOfWork;
import operations.bookReview.AddNewBookReview;
import operations.bookReview.UpdateBookReview;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by ivsi on 1/25/2016.
 */
@Path("books")
public class BookReviewResource {

    private final BookReviewDAO bookReviewDAO;
    private final RenteeDAO renteeDAO;
    private final BookDAO bookDAO;


    public BookReviewResource(BookReviewDAO bookReviewDAO, RenteeDAO renteeDAO, BookDAO bookDAO) {
        this.bookReviewDAO = bookReviewDAO;
        this.renteeDAO = renteeDAO;
        this.bookDAO = bookDAO;
    }

    @POST
    @Path("{bookId}/bookReviews/rentees/{renteeId}")
    @UnitOfWork
    public BookReview addBookReview(@PathParam("bookId") Long bookId, @PathParam("renteeId") Long renteeId, BookReview bookReview) {
        return new AddNewBookReview(bookReviewDAO, bookDAO, renteeDAO).execute(bookId, renteeId, bookReview);
    }

    @GET
    @Path("{bookId}/bookReviews")
    @UnitOfWork
    public List<BookReview> getBookReviews(@PathParam("bookId") Long bookId) {
        return bookReviewDAO.findByBookId(bookId);
    }

    @PUT
    @Path("{bookId}/bookReviews/{bookReviewId}")
    @UnitOfWork
    public BookReview updateBookReview(@PathParam("bookReviewId") Long bookReviewId, BookReview bookReview) {
        return new UpdateBookReview(bookReviewDAO).execute(bookReviewId, bookReview);
    }

}
