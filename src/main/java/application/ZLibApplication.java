package application;

import config.ZLibConfiguration;
import core.Book;
import core.BookReview;
import core.Rentee;
import dao.BookDAO;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resource.BookResource;
import resource.BookReviewResource;
import resource.RenteeResource;
import resource.ReservationResources;

/**
 * Created by ivsi on 1/25/2016.
 */
public class ZLibApplication extends Application<ZLibConfiguration> {

    private final HibernateBundle<ZLibConfiguration> hibernateBundle =
            new HibernateBundle<ZLibConfiguration>(Book.class, BookReview.class, Rentee.class) {
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

        final BookDAO bookDAO = new BookDAO(hibernateBundle.getSessionFactory());

        final BookResource bookResource = new BookResource(bookDAO);
        final BookReviewResource bookReviewResource = new BookReviewResource();
        final RenteeResource renteeResource = new RenteeResource();
        final ReservationResources reservationResources = new ReservationResources();

        environment.jersey().register(bookResource);
        environment.jersey().register(bookReviewResource);
        environment.jersey().register(renteeResource);
        environment.jersey().register(reservationResources);

    }

    @Override
    public void initialize(Bootstrap<ZLibConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }
}
