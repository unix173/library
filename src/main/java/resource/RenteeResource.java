package resource;

import core.Rentee;
import dao.RenteeDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    public long addRentee(Rentee rentee) {
        return renteeDAO.create(rentee);
    }

    @GET
    @UnitOfWork
    public List<Rentee> getRentees() {
        return renteeDAO.findAll();
    }


}
