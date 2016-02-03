package core.bookmedium;

import com.fasterxml.jackson.annotation.*;
import core.book.EBook;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ivsi on 2/1/2016.
 */
@NamedQueries({
        @NamedQuery(name = "EBookReader.findAll", query = "from EBookReader r")
})
@Entity
public class EBookReader implements BookMedium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eBookReaderId;
    private String uniqueName;
    private boolean available = true;

    public List<EBook> geteBookList() {
        return eBookList;
    }

    //should take a look
    @OneToMany(mappedBy = "eBookReader", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<EBook> eBookList;


    public Long geteBookReaderId() {
        return eBookReaderId;
    }

    public void seteBookReaderId(Long eBookReaderId) {
        this.eBookReaderId = eBookReaderId;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    @Override
    public MediumType getType() {
        return MediumType.E_READER;
    }

    @Override
    public String getUniqueName() {
        return uniqueName;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
