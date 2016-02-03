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

    public Reservation execute(Rentee rentee, Reservation reservation) {
        if (reservation.getBook() == null || rentee == null) {
            return null;
        }
        rentee = renteeDAO.findById(rentee.getUsername());
        Book bookToRent = bookDAO.findById(reservation.getBook().getBookId());
        Reservation reservationToCreate = null;
        if (rentee != null && bookToRent != null) {
            if ((bookToRent.isAvailable())) {
                reservationToCreate = new Reservation();
                reservationToCreate.setBook(bookToRent);
                reservationToCreate.setRentee(rentee);
                bookToRent.setAvailable(false);
                bookDAO.update(bookToRent);
            }
        }
        if (reservationToCreate == null) {
            return null;
        }
        return reservationDAO.create(reservationToCreate);
    }

}
