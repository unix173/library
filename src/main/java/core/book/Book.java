package core.book;

/**
 * Created by ivsi on 1/25/2016.
 */

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import core.bookmedium.BookMedium;
import core.BookReview;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

/**
 * Created by ivsi on 1/4/2016.
 */

@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "From Book b"),
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "book")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EBook.class, name = "eBook"),
        @JsonSubTypes.Type(value = PaperBook.class, name = "paperBook")})
public abstract class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
    private String title;
    private String author;
    @Column(nullable = true)
    private LocalDate year;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    @Column(nullable = true)
    @JsonManagedReference(value = "bookRef")
    private List<BookReview> bookReviews;

    public abstract boolean isAvailable();

    public abstract void setAvailable(boolean available);

    public abstract void rentBook();

    public abstract void returnBook();

    public abstract BookMedium getBookMedium();

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getYear() {
        return year;
    }

    public List<BookReview> getBookReviews() {
        if (bookReviews == null) {
            bookReviews = new ArrayList<>();
        }
        return bookReviews;
    }

    public void setBookReviews(List<BookReview> bookReviews) {
        this.bookReviews = bookReviews;
    }

    public void addReview(BookReview bookReview) {
        bookReviews.add(bookReview);
    }

    @Override
    public String toString() {
        return String.format("\nTile: %s Author: %s, Year: %s, Test Id: %s, Available: %s\n",
                title, author, year, bookId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }

        final Book that = (Book) o;

        return Objects.equals(this.bookId, that.bookId) &&
                Objects.equals(this.getTitle(), that.getTitle()) &&
                Objects.equals(this.getAuthor(), that.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, author);
    }
}
