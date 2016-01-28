package operations.book;

import core.Book;
import core.BookReview;
import dao.BookDAO;

/**
 * Created by ivsi on 1/28/2016.
 */
public class UpdateBook {

    private final BookDAO bookDAO;

    public UpdateBook(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Book execute(Long bookId, Book book) {
        Book bookToUpdate = bookDAO.findById(bookId);
        if (bookToUpdate != null) {
            if (book.getTitle() != null) {
                bookToUpdate.setTitle(book.getTitle());
            }
            if (book.getAuthor() != null) {
                bookToUpdate.setAuthor(book.getAuthor());
            }
            if (book.getYear() != null) {
                bookToUpdate.setYear(book.getYear());
            }
        }
        return bookDAO.update(bookToUpdate);
    }
}
