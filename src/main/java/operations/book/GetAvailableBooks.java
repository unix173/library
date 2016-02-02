package operations.book;

import core.book.Book;
import dao.BookDAO;

import java.util.List;

/**
 * Created by ivsi on 2/1/2016.
 */
public class GetAvailableBooks {
    private final BookDAO bookDAO;

    public GetAvailableBooks(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<Book> execute() {
        throw new UnsupportedOperationException();
    }
}
