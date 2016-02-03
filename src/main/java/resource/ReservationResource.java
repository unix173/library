package resource;

import core.Rentee;
import core.Reservation;
import dao.BookDAO;
import dao.RenteeDAO;
import dao.ReservationDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import operations.reservation.*;

import javax.annotation.security.PermitAll;
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
    public List<Reservation> getReservationsByRentee(@PathParam("renteeId") String renteeId) {
        return new GetReservationsByRentee(reservationDAO, bookDAO, renteeDAO).execute(renteeId);
    }

    @GET
    @Path("{reservationId}")
    @UnitOfWork
    public Reservation getReservationById(@PathParam("reservationId") Long reservationId) {
        return new GetReservationById(reservationDAO).execute(reservationId);
    }

    @PermitAll
    @POST
    @UnitOfWork
    public Reservation createReservation(@Auth Rentee rentee, Reservation reservation) {
        return new AddNewReservation(reservationDAO, bookDAO, renteeDAO).execute(rentee, reservation);
    }

    @PermitAll
    @PUT
    @UnitOfWork
    @Path("{reservationId}")
    public Reservation deactivateReservation(@Auth Rentee retnee, @PathParam("reservationId") Long reservationId) {
        return new DeactivateReservation(reservationDAO, bookDAO, renteeDAO).execute(retnee, reservationId);

    }

}

