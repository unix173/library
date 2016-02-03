package core;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.security.auth.Subject;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivsi on 1/4/2016.
 */
@NamedQueries({
        @NamedQuery(name = "Rentee.findAll", query = "From Rentee r"),
        @NamedQuery(name = "Rentee.findByUsername", query = "From Rentee r where r.username = :username")
})
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "@id")
public class Rentee implements Principal {

    @Id
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Role role;
    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "rentee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(nullable = true)
    @JsonManagedReference(value = "renteeRef")
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
            property = "@renteeId")
    private List<BookReview> reviews;

    public List<BookReview> getReviews() {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        return reviews;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("Username: %s\n", username);
    }

    @JsonIgnore
    @Override
    public String getName() {
        return username;
    }

    @Override
    public boolean implies(Subject subject) {
        return true;
    }

}
