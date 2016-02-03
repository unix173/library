package dao;

import core.Reservation;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by ivsi on 1/28/2016.
 */
public class ReservationDAO extends AbstractDAO<Reservation> {

    public ReservationDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Reservation create(Reservation reservation) {
        return persist(reservation);
    }

    public Reservation findById(Long resevationId) {
        return get(resevationId);
    }

    public Reservation delete(Reservation reservationToDelete) {
        currentSession().delete(reservationToDelete);
        return reservationToDelete;
    }

    public List<Reservation> findByRenteeId(String username) {
        return list(namedQuery("Reservation.findByUserId").setParameter("username", username));
    }

    public Reservation update(Reservation reservationToUpdate) {
        return persist(reservationToUpdate);
    }
}
