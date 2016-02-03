package core.book;

import core.bookmedium.BookMedium;
import core.bookmedium.EBookReader;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by ivsi on 2/2/2016.
 */
@Entity
@DiscriminatorValue("ebook")
public class EBook extends Book {

    @ManyToOne
    @JoinColumn(name = "eBookReaderId")
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

