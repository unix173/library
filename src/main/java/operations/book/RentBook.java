package operations.book;

import core.book.Book;
import dao.BookDAO;

/**
 * Created by ivsi on 1/28/2016.
 */
public class RentBook {

    private final BookDAO bookDAO;

    public RentBook(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Book execute(Long bookId) {
        Book newBook = bookDAO.findById(bookId);
        if ((newBook != null && newBook.isAvailable())) {
            newBook.setAvailable(false);
            bookDAO.update(newBook);
        }
        return newBook;
    }


}
