package application;

import config.ZLibConfiguration;
import core.Book;
import core.BookReview;
import core.Rentee;
import core.Reservation;
import dao.BookDAO;
import dao.BookReviewDAO;
import dao.RenteeDAO;
import dao.ReservationDAO;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resource.BookResource;
import resource.BookReviewResource;
import resource.RenteeResource;
import resource.ReservationResource;

/**
 * Created by ivsi on 1/25/2016.
 */
public class ZLibApplication extends Application<ZLibConfiguration> {

    private final HibernateBundle<ZLibConfiguration> hibernateBundle =
            new HibernateBundle<ZLibConfiguration>(Book.class, BookReview.class, Rentee.class, Reservation.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(ZLibConfiguration configuration) {
                    return configuration.getDatabase();
                }
            };

    public static void main(String[] args) throws Exception {
        new ZLibApplication().run(args);
    }

    @Override
    public void run(ZLibConfiguration zLibConfiguration, Environment environment) throws Exception {

        //register DAO objects
        final BookDAO bookDAO = new BookDAO(hibernateBundle.getSessionFactory());
        final RenteeDAO renteeDAO = new RenteeDAO(hibernateBundle.getSessionFactory());
        final BookReviewDAO bookReviewDAO = new BookReviewDAO(hibernateBundle.getSessionFactory());
        final ReservationDAO reservationDAO = new ReservationDAO(hibernateBundle.getSessionFactory());

        //register resources
        final BookResource bookResource = new BookResource(bookDAO);
        final BookReviewResource bookReviewResource = new BookReviewResource(bookReviewDAO, renteeDAO, bookDAO);
        final RenteeResource renteeResource = new RenteeResource(renteeDAO);
        final ReservationResource reservationResource = new ReservationResource(reservationDAO, bookDAO, renteeDAO);

        environment.jersey().register(bookResource);
        environment.jersey().register(bookReviewResource);
        environment.jersey().register(renteeResource);
        environment.jersey().register(reservationResource);
    }

    @Override
    public void initialize(Bootstrap<ZLibConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }
}
