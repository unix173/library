package core;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import core.book.Book;
import org.assertj.core.internal.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by ivsi on 1/28/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Reservation.findByRenteeId", query = "From Reservation r where r.rentee.username = :username"),
}
)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "@id")
public class Reservation {

    private static final int MAX_DURATION_DAYS = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookId")
    private Book book;

    @OneToOne()
    @JoinColumn(name = "renteeId")
    private Rentee rentee;

    private LocalDate creationDate;
    private boolean active;

    public Reservation() {
        active = true;
        creationDate = LocalDate.now();
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

    @JsonProperty
    public LocalDate getExpireDate() {
        return creationDate.plusDays(MAX_DURATION_DAYS);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
