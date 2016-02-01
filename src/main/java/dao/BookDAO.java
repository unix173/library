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

    public Book create(Book book) {
        return persist(book);
    }

    public List<Book> findAll() {
        return list(namedQuery("Book.findAll"));
    }

    public Book update(Book book) {
        return persist(book);
    }

    public Book delete(Book book) {
        currentSession().delete(book);
        return book;
    }

    public List<Book> findAvailable() {
        return list(namedQuery("Book.findAvailable"));
    }

}
