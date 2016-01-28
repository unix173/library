package operations.book;

import core.Book;
import dao.BookDAO;

/**
 * Created by ivsi on 1/28/2016.
 */
public class AddNewBook {

    private final BookDAO bookDAO;

    public AddNewBook(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Book execute(Book book) {
        return bookDAO.create(book);
    }
}
