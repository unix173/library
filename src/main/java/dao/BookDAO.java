package dao;

import core.Book;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by ivsi on 1/25/2016.
 */
public class BookDAO extends AbstractDAO<Book> {
    public BookDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Book findById(Long id) {
        return get(id);
    }

    public long create(Book book) {
        return persist(book).getBookId();
    }

    public List<Book> findAll() {
        return list(namedQuery("Book.findAll"));
    }
}
