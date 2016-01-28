package resource;

import core.Reservation;
import dao.ReservationDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by ivsi on 1/28/2016.
 */
@Path("reservations")
public class ReservationResource {

    private final ReservationDAO reservationDAO;

    public ReservationResource(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    @GET
    @Path("/users/{renteeId}")
    public List<Reservation> getReservations(@PathParam("renteeId") Long renteeId) {
        return reservationDAO.findByRenteeId(renteeId);
    }


    @GET
    @Path("{reservationId}")
    public Reservation getReservation(@PathParam("reservationId") Long reservationId) {
        return reservationDAO.findById(reservationId);
    }

}
