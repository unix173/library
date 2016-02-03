package operations.rentee;

import core.Rentee;
import dao.RenteeDAO;

/**
 * Created by ivsi on 1/28/2016.
 */
public class UpdateRentee {

    private final RenteeDAO renteeDAO;

    public UpdateRentee(RenteeDAO renteeDAO) {
        this.renteeDAO = renteeDAO;
    }

    public Rentee execute(Rentee rentee, Rentee renteeUpdateVals) {
        Rentee renteeToUpdate = renteeDAO.findById(rentee.getUsername());
        if ((renteeToUpdate != null)) {
            if (renteeUpdateVals.getPassword() != null) {
                renteeToUpdate.setPassword(renteeUpdateVals.getPassword());
            }
        }
        return renteeDAO.update(renteeToUpdate);
    }
}
