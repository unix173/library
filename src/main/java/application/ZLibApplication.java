package application;

import config.ZLibConfiguration;
import core.Book;
import core.BookReview;
import core.Rentee;
import dao.BookDAO;
import dao.BookReviewDAO;
import dao.RenteeDAO;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resource.BookResource;
import resource.BookReviewResource;
import resource.RenteeResource;

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
        final RenteeDAO renteeDAO = new RenteeDAO(hibernateBundle.getSessionFactory());
        final BookReviewDAO bookReviewDAO = new BookReviewDAO(hibernateBundle.getSessionFactory());

        final BookResource bookResource = new BookResource(bookDAO);
        final BookReviewResource bookReviewResource = new BookReviewResource(bookReviewDAO);
        final RenteeResource renteeResource = new RenteeResource(renteeDAO);

        environment.jersey().register(bookResource);
        environment.jersey().register(bookReviewResource);
        environment.jersey().register(renteeResource);

    }

    @Override
    public void initialize(Bootstrap<ZLibConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }
}
