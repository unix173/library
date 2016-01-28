package operations.bookReview;

import core.BookReview;
import dao.BookReviewDAO;

/**
 * Created by ivsi on 1/28/2016.
 */
public class AddNewReview {

    private final BookReviewDAO bookReviewDAO;

    public AddNewReview(BookReviewDAO bookReviewDAO) {
        this.bookReviewDAO = bookReviewDAO;
    }

    public BookReview execute(BookReview bookReview) {
        return bookReviewDAO.create(bookReview);
    }


}
