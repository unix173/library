package operations.book;

import core.Book;
import dao.BookDAO;

/**
 * Created by ivsi on 1/28/2016.
 */
public class ReturnBook {


    private final BookDAO bookDAO;

    public ReturnBook(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Book execute(Long bookId) {
        Book newBook = bookDAO.findById(bookId);
        if ((newBook != null && newBook.isAvailable())) {
            newBook.setAvailable(true);
            bookDAO.update(newBook);
        }
        return newBook;
    }
}
