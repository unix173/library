package core.book;

import core.bookmedium.BookMedium;
import core.bookmedium.HardCoverBook;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by ivsi on 2/2/2016.
 */
@Entity
@DiscriminatorValue("paperBook")
public class PaperBook extends Book {

    private boolean available = true;
    private static final HardCoverBook HARD_COVER_BOOK = new HardCoverBook();

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public BookMedium getBookMedium() {
        return HARD_COVER_BOOK;
    }
}
