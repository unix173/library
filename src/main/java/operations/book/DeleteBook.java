package operations.book;

import core.Book;
import dao.BookDAO;

/**
 * Created by ivsi on 1/28/2016.
 */
public class DeleteBook {

    private final BookDAO bookDAO;

    public DeleteBook(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Book execute(Long bookId) {
        Book bookToDelete = bookDAO.findById(bookId);
        return bookDAO.delete(bookToDelete);
    }

}
