package operations.reservation;

import core.Book;
import core.Rentee;
import core.Reservation;
import dao.BookDAO;
import dao.RenteeDAO;
import dao.ReservationDAO;

/**
 * Created by ivsi on 1/28/2016.
 */
public class AddNewReservation {

    private final ReservationDAO reservationDAO;
    private final BookDAO bookDAO;
    private final RenteeDAO renteeDAO;

    public AddNewReservation(ReservationDAO reservationDAO, BookDAO bookDAO, RenteeDAO renteeDAO) {
        this.reservationDAO = reservationDAO;
        this.bookDAO = bookDAO;
        this.renteeDAO = renteeDAO;
    }

    public Reservation execute(Long bookId, Long renteeId) {
        Rentee rentee = renteeDAO.findById(renteeId);
        Book bookToRent = bookDAO.findById(bookId);
        Reservation reservation = null;
        if (rentee != null && bookToRent != null) {
            reservation = new Reservation(bookToRent, rentee);
            if ((bookToRent.isAvailable())) {
                bookToRent.setAvailable(false);
                bookDAO.update(bookToRent);
                reservation = new Reservation(bookToRent, rentee);
            }
        }
        return reservationDAO.create(reservation);
    }

}
