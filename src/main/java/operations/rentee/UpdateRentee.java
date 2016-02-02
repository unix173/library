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

    public Rentee execute(Long renteId, Rentee rentee) {
        Rentee renteeToUpdate = renteeDAO.findById(renteId);
        if ((renteeToUpdate != null)) {
            if (rentee.getPassword() != null) {
                renteeToUpdate.setPassword(rentee.getPassword());
            }
        }
        return renteeDAO.update(renteeToUpdate);
    }
}
