package core;

/**
 * Created by ivsi on 1/25/2016.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

/**
 * Created by ivsi on 1/4/2016.
 */

@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "From Book b"),
        @NamedQuery(name = "Book.findAvailable", query = "From Book b where b.isAvailable = true")
})
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(required = false)
    @JsonIgnore
    private Long bookId;

    private String title;

    @JsonProperty(required = false)
    private String author;

    @JsonProperty(required = false)
    @Column(nullable = true)
    private LocalDate year;

    @JsonProperty(required = false)
    private boolean available;

    @OneToMany(mappedBy = "operations/book", fetch = FetchType.EAGER)
    @Column(nullable = true)
    @JsonManagedReference(value = "bookRef")
    @JsonProperty(required = false)
    private List<BookReview> bookReviews;

    public Book() {
    }

    public Book(long bookId, String title, String author, LocalDate year, List<BookReview> bookReviews) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.year = year;
        this.bookReviews = bookReviews;
        this.available = true;
    }

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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<BookReview> getBookReviews() {
        return bookReviews;
    }

    public void setBookReviews(List<BookReview> bookReviews) {
        this.bookReviews = bookReviews;
    }

    public void addReview(BookReview bookReview) {
        bookReviews.add(bookReview);
    }

    public void updateFrom(Book other) {
        this.available = other.available;
    }

    @Override
    public String toString() {
        return String.format("\nTile: %s Author: %s, Year: %s, Test Id: %s, Available: %s\n",
                title, author, year, bookId, available);
    }
}
