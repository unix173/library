package core.book;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import core.bookmedium.BookMedium;
import core.bookmedium.EBookReader;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/**
 * Created by ivsi on 2/2/2016.
 */
@Entity
@DiscriminatorValue("ebook")

public class EBook extends Book {

    @ManyToOne(cascade = CascadeType.ALL)
    private EBookReader eBookReader;

    @Override
    public boolean isAvailable() {
        return eBookReader.isAvailable();
    }

    @Override
    public void setAvailable(boolean available) {
        this.eBookReader.setAvailable(available);
    }

    @Override
    public BookMedium getBookMedium() {
        return eBookReader;
    }

    public void seteBookReader(EBookReader eBookReader) {
        this.eBookReader = eBookReader;
    }
}

