package operations.reservation;

import core.Reservation;
import dao.BookDAO;
import dao.RenteeDAO;
import dao.ReservationDAO;

import java.util.List;

/**
 * Created by ivsi on 2/1/2016.
 */
public class GetReservationsByRentee {

    private final ReservationDAO reservationDAO;
    private final BookDAO bookDAO;
    private final RenteeDAO renteeDAO;

    public GetReservationsByRentee(ReservationDAO reservationDAO, BookDAO bookDAO, RenteeDAO renteeDAO) {
        this.reservationDAO = reservationDAO;
        this.bookDAO = bookDAO;
        this.renteeDAO = renteeDAO;
    }

    public List<Reservation> execute(Long renteeId) {
        return reservationDAO.findByRenteeId(renteeId);
    }
}
