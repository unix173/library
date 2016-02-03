package resource;

import core.BookReview;
import core.Rentee;
import dao.BookDAO;
import dao.BookReviewDAO;
import dao.RenteeDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import operations.bookReview.AddNewBookReview;
import operations.bookReview.DeleteBookReview;
import operations.bookReview.GetBookReviews;
import operations.bookReview.UpdateBookReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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

    @PermitAll
    @POST
    @Path("/bookreviews")
    @UnitOfWork
    public BookReview addBookReview(@Auth Rentee rentee, BookReview bookReview) {
        return new AddNewBookReview(bookReviewDAO, bookDAO, renteeDAO).execute(rentee, bookReview);
    }

    @PermitAll
    @GET
    @Path("{bookId}/bookReviews")
    @UnitOfWork
    public List<BookReview> getBookReviews(@PathParam("bookId") Long bookId) {
        return new GetBookReviews(bookReviewDAO).execute(bookId);
    }

    @PermitAll
    @PUT
    @Path("/bookReviews/{bookReviewId}")
    @UnitOfWork
    public BookReview updateBookReview(@Auth Rentee rentee, @PathParam("bookReviewId") Long bookReviewId, BookReview bookReview) {
        return new UpdateBookReview(bookReviewDAO).execute(rentee, bookReviewId, bookReview);
    }

    @PermitAll
    @DELETE
    @Path("/bookReviews/{bookReviewId}")
    @UnitOfWork
    public BookReview deleteBookReview(@Auth Rentee rentee, @PathParam("bookReviewId") Long bookReviewId) {
        return new DeleteBookReview(bookReviewDAO).execute(bookReviewId);
    }

}
