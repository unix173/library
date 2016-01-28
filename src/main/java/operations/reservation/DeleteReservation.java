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
public class DeleteReservation {

    private final ReservationDAO reservationDAO;
    private final RenteeDAO renteeDAO;
    private final BookDAO bookDAO;


    public DeleteReservation(ReservationDAO reservationDAO, BookDAO bookDAO, RenteeDAO renteeDAO) {
        this.reservationDAO = reservationDAO;
        this.renteeDAO = renteeDAO;
        this.bookDAO = bookDAO;
    }

    public Reservation execute(Long resevationId) {
        Reservation reservationToDelete = reservationDAO.findById(resevationId);
        if (reservationToDelete != null) {
            Book bookToUpdate = reservationToDelete.getBook();
            bookToUpdate.setAvailable(true);
            bookDAO.update(bookToUpdate);
        }

        return reservationDAO.delete(reservationToDelete);
    }
}
