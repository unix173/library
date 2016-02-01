package resource;

import core.Rentee;
import dao.RenteeDAO;
import io.dropwizard.hibernate.UnitOfWork;
import operations.rentee.AddNewRentee;
import operations.rentee.DeleteRentee;
import operations.rentee.GetAllRentees;
import operations.rentee.UpdateRentee;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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

    @POST
    @UnitOfWork
    public Rentee addRentee(Rentee rentee) {
        return new AddNewRentee(renteeDAO).execute(rentee);
    }

    @GET
    @UnitOfWork
    public List<Rentee> getRentees() {
        return new GetAllRentees(renteeDAO).execute();
    }

    @DELETE
    @Path("/{renteeId}")
    @UnitOfWork
    public Rentee deleteRentee(@PathParam("renteeId") Long renteeId) {
        return new DeleteRentee(renteeDAO).execute(renteeId);
    }

    @PUT
    @Path("{renteeId}")
    @UnitOfWork
    public Rentee updateRentee(@PathParam("renteeId") Long renteeId, Rentee rentee) {
        return new UpdateRentee(renteeDAO).execute(renteeId, rentee);
    }


}
