package auth;

import com.google.common.base.Optional;
import core.Rentee;
import dao.RenteeDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;
import operations.rentee.GetAllRentees;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;

import java.util.List;

/**
 * Created by ivsi on 2/2/2016.
 */
public class UserAuthenticator implements Authenticator<BasicCredentials, Rentee> {

    private final RenteeDAO renteeDAO;
    private SessionFactory sessionFactory;

    public UserAuthenticator(RenteeDAO renteeDAO, SessionFactory sessionFactory) {
        this.renteeDAO = renteeDAO;
        this.sessionFactory = sessionFactory;
    }


    @Override
    public com.google.common.base.Optional<Rentee> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        Session session = sessionFactory.openSession();
        try {
            ManagedSessionContext.bind(session);
            try {
                List<Rentee> rentees = new GetAllRentees(renteeDAO).execute();
                final Rentee destinctUser = rentees.stream().filter(user -> user.getPassword().equals(basicCredentials.getPassword()) && user.getUsername().equals(basicCredentials.getUsername())).findFirst().get();
                if (destinctUser != null) {
                    return Optional.of(destinctUser);
                }
            } catch (Exception e) {
                return Optional.absent();
            }
        } finally {
            session.close();
            ManagedSessionContext.unbind(sessionFactory);
        }
        return Optional.absent();
    }


}
