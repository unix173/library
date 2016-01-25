package core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Created by ivsi on 1/25/2016.
 */
@Entity
public class Reservation {

    public static int MAX_RESERVATION_DURATION = 30;

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate reservationBegin;
    private LocalDate reservationEnd;
    //agregation relation
    private Book book;
    private Rentee user;

    public Reservation(Book book, Rentee user, LocalDate reservationBegin) {
        this.book = book;
        this.user = user;
        this.reservationBegin = reservationBegin;
        this.reservationEnd = reservationBegin.plusDays(MAX_RESERVATION_DURATION);
    }

    public long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Rentee getRentee() {
        return user;
    }

    public LocalDate getReservationBegin() {
        return reservationBegin;
    }

    public LocalDate getReservationEnd() {
        return reservationEnd;
    }

    @Override
    public String toString() {
        return String.format("Book: %s, Rentee: %s, From: %s, To: %s, Ending: %s\n",
                book.getBookId(), user.getUsername(), reservationBegin, reservationEnd);
    }

}
