package dao;

import core.Reservation;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by ivsi on 1/25/2016.
 */
public class ReservationDAO extends AbstractDAO<Reservation> {
    public ReservationDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
