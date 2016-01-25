package core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivsi on 1/25/2016.
 */
@Entity
public class Rentee {
    @Id
    @GeneratedValue
    private Long renteeId;

    @JsonProperty(required = false)
    private String username;
    @JsonProperty(required = false)
    private String password;
    @JsonProperty(required = false)
    private boolean loggedIn;
    @JsonProperty(required = false)
    private List<Reservation> reservations;
    @JsonProperty(required = false)
    @OneToMany(mappedBy = "rentee")
    private List<BookReview> reviews;

    public Rentee() {
    }

    private Rentee(Long renteeId) {
        this.renteeId = renteeId;
        reservations = new ArrayList<>();
        reviews = new ArrayList<>();
    }

    public Rentee(String username, String password, boolean loggedIn, List<Reservation> reservations, List<BookReview> reviews) {
        this.username = username;
        this.password = password;
        this.loggedIn = loggedIn;
        this.reservations = reservations;
        this.reviews = reviews;
    }



    public List<BookReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<BookReview> reviews) {
        this.reviews = reviews;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public String toString() {
        return String.format("Username: %s\n", username);
    }

    public Long getRenteeId() {
        return renteeId;
    }

    public void setRenteeId(Long renteeId) {
        this.renteeId = renteeId;
    }

}

