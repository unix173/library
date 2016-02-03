package resource;

import core.Rentee;
import dao.RenteeDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import operations.rentee.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by ivsi on 1/25/2016.
 */
@Path("rentees")
public class RenteeResource {
    private RenteeDAO renteeDAO;

    public RenteeResource(RenteeDAO renteeDAO) {
        this.renteeDAO = renteeDAO;
    }

    @RolesAllowed("ADMIN")
    @POST
    @UnitOfWork
    public Rentee addRentee(Rentee rentee) {
        return new AddNewRentee(renteeDAO).execute(rentee);
    }

    @RolesAllowed("ADMIN")
    @GET
    @UnitOfWork
    public List<Rentee> getRentees() {
        return new GetAllRentees(renteeDAO).execute();
    }

    @RolesAllowed("ADMIN")
    @PermitAll
    @DELETE
    @Path("{renteeId}")
    @UnitOfWork
    public Rentee deleteRentee(@PathParam("renteeId") String renteeId) {
        return new DeleteRentee(renteeDAO).execute(renteeId);
    }

    @PermitAll
    @PUT
    @UnitOfWork
    public Rentee updateRentee(@Auth Rentee rentee, Rentee renteeUpdateVals) {
        return new UpdateRentee(renteeDAO).execute(rentee, renteeUpdateVals);
    }

    @PermitAll
    @Path("/personal")
    @GET
    @UnitOfWork
    public Rentee getRentees(@Auth Rentee rentee) {
        return new GetCurrentRentee(renteeDAO).execute(rentee);
    }

}
