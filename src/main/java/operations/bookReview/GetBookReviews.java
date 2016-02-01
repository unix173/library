package operations.bookReview;

import core.BookReview;
import dao.BookReviewDAO;

import java.util.List;

/**
 * Created by ivsi on 2/1/2016.
 */
public class GetBookReviews {

    private final BookReviewDAO bookReviewDAO;

    public GetBookReviews(BookReviewDAO bookReviewDAO) {
        this.bookReviewDAO = bookReviewDAO;
    }

    public List<BookReview> execute(Long bookId) {
        return bookReviewDAO.findByBookId(bookId);
    }
}
