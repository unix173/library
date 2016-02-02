package core;

import core.book.Book;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by ivsi on 1/28/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Reservation.findByRenteeId", query = "From Reservation r where r.rentee.renteeId = :renteeId"),
}
)
public class Reservation {

    private static final int MAX_DURATION_DAYS = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @OneToOne()
    @JoinColumn(name = "bookId", insertable = false, updatable = false)
    private Book book;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "renteeId", insertable = false, updatable = false)
    private Rentee rentee;

    private LocalDate creationDate;

    public Reservation() {
        creationDate = LocalDate.now();
    }

    public Reservation(Book book, Rentee rentee) {
        super();
        this.book = book;
        this.rentee = rentee;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Rentee getRentee() {
        return rentee;
    }

    public void setRentee(Rentee rentee) {
        this.rentee = rentee;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
