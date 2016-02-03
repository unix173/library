package operations.book;

import core.book.Book;
import dao.BookDAO;

/**
 * Created by ivsi on 2/3/2016.
 */
public class GetBookById {

    private final BookDAO bookDAO;

    public GetBookById(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Book execute(Long bookId) {
        return bookDAO.findById(bookId);
    }
}
