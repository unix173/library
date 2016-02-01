package core;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivsi on 1/4/2016.
 */
@NamedQueries({
        @NamedQuery(name = "Rentee.findAll", query = "From Rentee r")
})
@Entity
public class Rentee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long renteeId;

    private String username;
    private String password;

    @OneToMany(mappedBy = "rentee", fetch = FetchType.EAGER)
    @Column(nullable = true)
    @JsonManagedReference(value = "renteeRef")
    private List<BookReview> reviews;

    public Rentee() {
        reviews = new ArrayList<>();
    }

    private Rentee(Long renteeId) {
        this.renteeId = renteeId;
        reviews = new ArrayList<>();
    }

    public Long getRenteeId() {
        return renteeId;
    }

    public void setRenteeId(Long renteeId) {
        this.renteeId = renteeId;
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

    @Override
    public String toString() {
        return String.format("Username: %s\n", username);
    }

}
