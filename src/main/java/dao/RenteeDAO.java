package dao;

import core.Rentee;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by ivsi on 1/26/2016.
 */
public class RenteeDAO extends AbstractDAO<Rentee> {
    public RenteeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Rentee> findAll() {
        return list(namedQuery("Rentee.findAll"));
    }

    public long create(Rentee rentee) {
        return persist(rentee).getRenteeId();
    }

}
