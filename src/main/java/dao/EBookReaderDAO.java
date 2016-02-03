package dao;

import core.book.EBook;
import core.bookmedium.EBookReader;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by ivsi on 2/2/2016.
 */
public class EBookReaderDAO extends AbstractDAO<EBookReader> {

    public EBookReaderDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public EBookReader create(EBookReader eBookReader) {
        return persist(eBookReader);
    }

    public List<EBookReader> findAll() {
        return list(namedQuery("EBookReader.findAll"));
    }

    public EBookReader findById(Long readerID) {
        return get(readerID);
    }

    public EBookReader delete(Long rederId) {
        EBookReader eBookReader = findById(rederId);
        currentSession().delete(eBookReader);
        return eBookReader;
    }
}
