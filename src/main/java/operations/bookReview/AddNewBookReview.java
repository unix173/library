package operations.bookReview;

import core.book.Book;
import core.BookReview;
import core.Rentee;
import dao.BookDAO;
import dao.BookReviewDAO;
import dao.RenteeDAO;

/**
 * Created by ivsi on 1/28/2016.
 */
public class AddNewBookReview {

    private final BookReviewDAO bookReviewDAO;
    private final BookDAO bookDAO;
    private final RenteeDAO renteeDAO;

    public AddNewBookReview(BookReviewDAO bookReviewDAO, BookDAO bookDAO, RenteeDAO renteeDAO) {
        this.bookReviewDAO = bookReviewDAO;
        this.bookDAO = bookDAO;
        this.renteeDAO = renteeDAO;
    }

    public BookReview execute(Rentee rentee, BookReview bookReview) {
        Book book = bookDAO.findById(bookReview.getBook().getBookId());
        if (book != null) {
            rentee = renteeDAO.findById(rentee.getUsername());
            if (rentee != null) {
                bookReview.setBook(book);
                bookReview.setRentee(rentee);
            }
        }
        return bookReviewDAO.create(bookReview);
    }


}
