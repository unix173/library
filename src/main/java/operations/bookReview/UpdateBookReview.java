package operations.bookReview;

import core.BookReview;
import dao.BookReviewDAO;

/**
 * Created by ivsi on 1/28/2016.
 */
public class UpdateBookReview {

    private final BookReviewDAO bookReviewDAO;

    public UpdateBookReview(BookReviewDAO bookReviewDAO) {
        this.bookReviewDAO = bookReviewDAO;
    }

    public BookReview execute(Long bookReviewId, BookReview bookReview) {
        BookReview bookReviewToUpdate = bookReviewDAO.findById(bookReviewId);
        if (bookReviewToUpdate != null) {
            if (bookReview.getTitle() != null) {
                bookReviewToUpdate.setTitle(bookReview.getTitle());
            }
            if (bookReview.getText() != null) {
                bookReviewToUpdate.setText(bookReview.getText());
            }
        }
        return bookReviewDAO.update(bookReviewToUpdate);
    }


}
