package resource;

import core.Book;
import core.BookReview;
import dao.BookReviewDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    @Path("{bookId}/bookReviews")
    @POST
    @UnitOfWork
    public Long addBookReview(BookReview bookReview) {
        return bookReviewDAO.create(bookReview);
    }

    @GET
    @Path("{bookId}/bookReviews")
    @UnitOfWork
    public List<BookReview> getBookReviews(@PathParam("bookId") Long bookId) {
        return bookReviewDAO.findByBookId(bookId);
    }

}
