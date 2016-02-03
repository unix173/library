package operations.book;

import core.book.Book;
import dao.BookDAO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ivsi on 2/3/2016.
 */
public class GetAvailableBooks {
    private final BookDAO bookDAO;

    public GetAvailableBooks(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    //TODO: should check for a better way
    public List<Book> execute() {
        return bookDAO.findAll().stream().filter(x -> x.isAvailable()).collect(Collectors.toList());
    }
}
