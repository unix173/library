package application;

import auth.UserAuthenticator;
import auth.UserAuthorizer;
import config.ZLibConfiguration;
import core.book.Book;
import core.BookReview;
import core.Rentee;
import core.Reservation;
import core.book.EBook;
import core.book.PaperBook;
import core.bookmedium.EBookReader;
import dao.*;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resource.*;

/**
 * Created by ivsi on 1/25/2016.
 */
public class ZLibApplication extends Application<ZLibConfiguration> {

    private final HibernateBundle<ZLibConfiguration> hibernateBundle =
            new HibernateBundle<ZLibConfiguration>(Book.class, BookReview.class, Rentee.class, Reservation.class, PaperBook.class, EBook.class, EBookReader.class) {
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
        final EBookReaderDAO eBookReaderDAO = new EBookReaderDAO(hibernateBundle.getSessionFactory());

        //register resources
        final BookResource bookResource = new BookResource(bookDAO);
        final BookReviewResource bookReviewResource = new BookReviewResource(bookReviewDAO, renteeDAO, bookDAO);
        final RenteeResource renteeResource = new RenteeResource(renteeDAO);
        final ReservationResource reservationResource = new ReservationResource(reservationDAO, bookDAO, renteeDAO);
        final EBookReaderResource eBookReaderResource = new EBookReaderResource(eBookReaderDAO);

        //register jersey routes (resources)
        environment.jersey().register(bookResource);
        environment.jersey().register(bookReviewResource);
        environment.jersey().register(renteeResource);
        environment.jersey().register(reservationResource);
        environment.jersey().register(eBookReaderResource);
        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<Rentee>()
                .setAuthenticator(new UserAuthenticator(renteeDAO, hibernateBundle.getSessionFactory()))
                .setAuthorizer(new UserAuthorizer())
                .buildAuthFilter()));
    }

    @Override
    public void initialize(Bootstrap<ZLibConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }
}
