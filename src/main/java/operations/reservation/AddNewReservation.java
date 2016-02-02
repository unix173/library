package operations.reservation;

import core.book.Book;
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

    public Reservation execute(Reservation reservation) {
        if (reservation.getBook() == null || reservation.getRentee() == null) {
            return null;
        }
        Rentee rentee = renteeDAO.findById(reservation.getRentee().getRenteeId());
        Book bookToRent = bookDAO.findById(reservation.getBook().getBookId());
        Reservation reservationToCreate = null;
        if (rentee != null && bookToRent != null) {
            if ((bookToRent.isAvailable())) {
                reservationToCreate = new Reservation(bookToRent, rentee);
                bookToRent.setAvailable(false);
                bookDAO.update(bookToRent);
            }
        }
        return reservationDAO.create(reservationToCreate);
    }

}
