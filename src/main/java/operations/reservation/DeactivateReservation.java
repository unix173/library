package operations.reservation;

import core.Rentee;
import core.Reservation;
import core.Role;
import core.book.Book;
import dao.BookDAO;
import dao.RenteeDAO;
import dao.ReservationDAO;

/**
 * Created by ivsi on 2/3/2016.
 */
public class DeactivateReservation {

    private final ReservationDAO reservationDAO;
    private final BookDAO bookDAO;
    private final RenteeDAO renteeDAO;

    public DeactivateReservation(ReservationDAO reservationDAO, BookDAO bookDAO, RenteeDAO renteeDAO) {
        this.reservationDAO = reservationDAO;
        this.bookDAO = bookDAO;
        this.renteeDAO = renteeDAO;
    }

    public Reservation execute(Rentee rentee, Long reservationId) {
        rentee = renteeDAO.findById(rentee.getUsername());
        Reservation reservationToUpdate = reservationDAO.findById(reservationId);
        if (reservationToUpdate == null || rentee == null && !reservationToUpdate.isActive()) {
            return null;
        }
        if (rentee.getRole().equals(Role.ADMIN) || rentee.getUsername().equals(reservationToUpdate.getRentee().getUsername())) {
            Book bookToReturn = reservationToUpdate.getBook();
            bookToReturn.setAvailable(true);
            bookDAO.update(bookToReturn);
            reservationToUpdate.setActive(false);
            return reservationDAO.update(reservationToUpdate);
        }
        return null;
    }
}
