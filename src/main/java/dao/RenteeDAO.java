package dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by ivsi on 1/25/2016.
 */
public class RenteeDAO extends AbstractDAO<RenteeDAO> {
    public RenteeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
