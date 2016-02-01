package operations.reservation;

import core.Reservation;
import dao.BookDAO;
import dao.RenteeDAO;
import dao.ReservationDAO;

import java.util.List;

/**
 * Created by ivsi on 2/1/2016.
 */

public class GetReservationById {

    private final ReservationDAO reservationDAO;

    public GetReservationById(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    public Reservation execute(Long reservationId) {
        return reservationDAO.findById(reservationId);
    }


}
