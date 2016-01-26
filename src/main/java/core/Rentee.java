package core;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivsi on 1/4/2016.
 */
@Entity
public class Rentee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long renteeId;
    @JsonProperty(required = false)
    private String username;
    @JsonProperty(required = false)
    private String password;
    @JsonProperty(required = false)
    private boolean loggedIn;
    @JsonProperty(required = false)

    @OneToMany(mappedBy = "rentee")
    @JsonManagedReference
    private List<BookReview> reviews;

    public Rentee() {
    }

    private Rentee(Long renteeId) {
        this.renteeId = renteeId;
        reviews = new ArrayList<>();
    }

    public Rentee(Long renteeId, String username, String password) {
        this.username = username;
        this.password = password;
        loggedIn = false;
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
