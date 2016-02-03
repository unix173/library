package operations.rentee;

import core.Rentee;
import dao.RenteeDAO;

/**
 * Created by ivsi on 2/3/2016.
 */
public class GetCurrentRentee {
    private final RenteeDAO renteeDAO;

    public GetCurrentRentee(RenteeDAO renteeDAO) {
        this.renteeDAO = renteeDAO;
    }

    public Rentee execute(Rentee rentee) {
        return renteeDAO.findById(rentee.getUsername());
    }
}
