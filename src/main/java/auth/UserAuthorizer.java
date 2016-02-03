package auth;

import core.Rentee;
import io.dropwizard.auth.Authorizer;

/**
 * Created by ivsi on 2/2/2016.
 */
public class UserAuthorizer implements Authorizer<Rentee> {

    @Override
    public boolean authorize(Rentee rentee, String role) {
        if (!role.equals(rentee.getRole())) {
            return false;
        }
        return true;
    }
}
