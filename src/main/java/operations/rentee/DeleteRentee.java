package operations.rentee;

import core.Rentee;
import dao.RenteeDAO;

/**
 * Created by ivsi on 1/28/2016.
 */
public class DeleteRentee {

    private final RenteeDAO renteeDAO;

    public DeleteRentee(RenteeDAO renteeDAO) {
        this.renteeDAO = renteeDAO;
    }

    public Rentee execute(Long renteeId) {
        Rentee renteeToDelete = renteeDAO.findById(renteeId);
        return renteeDAO.delete(renteeToDelete);
    }
}
