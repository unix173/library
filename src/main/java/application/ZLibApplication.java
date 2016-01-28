package application;

import config.ZLibConfiguration;
import core.Book;
import core.BookReview;
import core.Rentee;
import dao.BookDAO;
import dao.BookReviewDAO;
import dao.RenteeDAO;
import dao.ReservationDAO;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import operations.book.AddNewBook;
import operations.book.DeleteBook;
import operations.book.UpdateBook;
import operations.bookReview.AddNewBookReview;
import operations.bookReview.DeleteBookReview;
import operations.bookReview.UpdateBookReview;
import operations.rentee.AddNewRentee;
import operations.rentee.DeleteRentee;
import operations.rentee.UpdateRentee;
import operations.reservation.AddNewReservation;
import operations.reservation.DeleteReservation;
import resource.BookResource;
import resource.BookReviewResource;
import resource.RenteeResource;
import resource.ReservationResource;

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

        //register DAO objects
        final BookDAO bookDAO = new BookDAO(hibernateBundle.getSessionFactory());
        final RenteeDAO renteeDAO = new RenteeDAO(hibernateBundle.getSessionFactory());
        final BookReviewDAO bookReviewDAO = new BookReviewDAO(hibernateBundle.getSessionFactory());
        final ReservationDAO reservationDAO = new ReservationDAO(hibernateBundle.getSessionFactory());

        //register operation objects
//        final AddNewBook addNewBook = new AddNewBook(bookDAO);
//        final DeleteBook deleteBook = new DeleteBook(bookDAO);
//        final UpdateBook updateBook = new UpdateBook(bookDAO);
//        final AddNewBookReview addNewBookReview = new AddNewBookReview(bookReviewDAO);
//        final DeleteBookReview deleteBookReview = new DeleteBookReview(bookReviewDAO);
//        final UpdateBookReview updateBookReview = new UpdateBookReview(bookReviewDAO);
//        final AddNewRentee addNewRentee = new AddNewRentee(renteeDAO);
//        final UpdateRentee updateRentee = new UpdateRentee(renteeDAO);
//        final DeleteRentee deleteRentee = new DeleteRentee(renteeDAO);
//        final AddNewReservation addNewReservation = new AddNewReservation(reservationDAO, bookDAO, renteeDAO);
//        final DeleteReservation deleteReservation = new DeleteReservation(reservationDAO, bookDAO, renteeDAO);


        //register resources
        final BookResource bookResource = new BookResource(bookDAO);
        final BookReviewResource bookReviewResource = new BookReviewResource(bookReviewDAO);
        final RenteeResource renteeResource = new RenteeResource(renteeDAO);
        final ReservationResource reservationResource = new ReservationResource(reservationDAO);

        environment.jersey().register(bookResource);
        environment.jersey().register(bookReviewResource);
        environment.jersey().register(renteeResource);

    }

    @Override
    public void initialize(Bootstrap<ZLibConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }
}
