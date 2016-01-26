package core;

/**
 * Created by ivsi on 1/25/2016.
 */

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivsi on 1/4/2016.
 */
//@NamedQueries({
//        @NamedQuery(
//                name = "findAll",
//                query = "from Book b"
//        )
//})
@Entity
public class Book {

    @Id
    @GeneratedValue
    private long bookId;
    private String title;
    @JsonProperty(required = false)
    private String author;
    @JsonProperty(required = false)
    private LocalDate year;
    @JsonProperty(required = false)
    private boolean available;
    @JsonProperty(required = false)

    @OneToMany(mappedBy = "book")
    private List<BookReview> bookReviews;

    public Book() {
        available = true;
        bookReviews = new ArrayList<>();
    }

    public Book(long bookId, String title, String author, LocalDate year) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = true;
        this.bookReviews = new ArrayList<>();
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

    @JsonProperty
    public long getBookId() {
        return bookId;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public String getAuthor() {
        return author;
    }

    @JsonProperty
    public LocalDate getYear() {
        return year;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @JsonProperty
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
        return String.format("\nTile: %s Author: %s, Year: %s, Book Id: %s, Available: %s\n",
                title, author, year, bookId, available);
    }
}
