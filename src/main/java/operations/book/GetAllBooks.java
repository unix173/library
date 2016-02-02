package operations.book;

import core.book.Book;
import dao.BookDAO;

import java.util.List;

/**
 * Created by ivsi on 2/1/2016.
 */
public class GetAllBooks {
    private final BookDAO bookDAO;

    public GetAllBooks(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<Book> execute() {
        return bookDAO.findAll();
    }
}
