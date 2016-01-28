package operations.bookReview;

import core.BookReview;
import dao.BookReviewDAO;

/**
 * Created by ivsi on 1/28/2016.
 */
public class DeleteBookReview {

    private final BookReviewDAO bookReviewDAO;

    public DeleteBookReview(BookReviewDAO bookReviewDAO) {
        this.bookReviewDAO = bookReviewDAO;
    }

    public BookReview execute(Long bookReviewId) {
        BookReview bookReviewToDelete = bookReviewDAO.findById(bookReviewId);
        return bookReviewDAO.delete(bookReviewToDelete);
    }
}
