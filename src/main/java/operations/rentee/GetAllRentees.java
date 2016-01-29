package operations.rentee;

import core.Rentee;
import dao.RenteeDAO;

import java.util.List;

/**
 * Created by ivsi on 1/29/2016.
 */
public class GetAllRentees {

    private final RenteeDAO renteeDAO;

    public GetAllRentees(RenteeDAO renteeDAO) {
        this.renteeDAO = renteeDAO;
    }

    public List<Rentee> execute() {
        return renteeDAO.findAll();
    }
}
