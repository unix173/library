package resource;

import core.Reservation;
import dao.BookDAO;
import dao.RenteeDAO;
import dao.ReservationDAO;
import io.dropwizard.hibernate.UnitOfWork;
import operations.reservation.AddNewReservation;
import operations.reservation.DeleteReservation;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by ivsi on 1/28/2016.
 */
@Path("reservations")
public class ReservationResource {

    private final ReservationDAO reservationDAO;
    private final BookDAO bookDAO;
    private final RenteeDAO renteeDAO;

    public ReservationResource(ReservationDAO reservationDAO, BookDAO bookDAO, RenteeDAO renteeDAO) {
        this.reservationDAO = reservationDAO;
        this.bookDAO = bookDAO;
        this.renteeDAO = renteeDAO;
    }

    @GET
    @Path("/rentees/{renteeId}")
    @UnitOfWork
    public List<Reservation> getReservationsByRentee(@PathParam("renteeId") Long renteeId) {
        return reservationDAO.findByRenteeId(renteeId);
    }

    @GET
    @Path("{reservationId}")
    @UnitOfWork
    public Reservation getReservationById(@PathParam("reservationId") Long reservationId) {
        return reservationDAO.findById(reservationId);
    }

    @POST
    @Path("books/{bookId}/rentees/{renteeId}}")
    @UnitOfWork
    public Reservation createReservation(@PathParam("bookId") Long bookId, @PathParam("renteeId") Long renteeId) {
        return new AddNewReservation(reservationDAO, bookDAO, renteeDAO).execute(bookId, renteeId);
    }

    @DELETE
    @Path("{reservationId}")
    @UnitOfWork
    public Reservation deleteReservation(@PathParam("reservationId") Long reservationId) {
        return new DeleteReservation(reservationDAO, bookDAO, renteeDAO).execute(reservationId);
    }


}
