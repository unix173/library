package operations.rentee;

import core.Rentee;
import dao.RenteeDAO;

/**
 * Created by ivsi on 1/28/2016.
 */
public class AddNewRentee {

    private final RenteeDAO renteeDAO;

    public AddNewRentee(RenteeDAO renteeDAO) {
        this.renteeDAO = renteeDAO;
    }

    public Rentee execute(Rentee rentee) {
        return renteeDAO.create(rentee);
    }


}

