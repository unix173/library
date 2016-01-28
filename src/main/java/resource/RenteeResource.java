package resource;

import core.Rentee;
import dao.RenteeDAO;
import io.dropwizard.hibernate.UnitOfWork;

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
        return renteeDAO.create(rentee);
    }

    @GET
    @UnitOfWork
    public List<Rentee> getRentees() {
        return renteeDAO.findAll();
    }


}
