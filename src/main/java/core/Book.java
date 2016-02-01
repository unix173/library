package core;

/**
 * Created by ivsi on 1/25/2016.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
        @NamedQuery(name = "Book.findAvailable", query = "From Book b where b.available = true")
})
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String title;
    private String author;
    @Column(nullable = true)
    private LocalDate year;
    private boolean available;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    @Column(nullable = true)
    @JsonManagedReference(value = "bookRef")
    private List<BookReview> bookReviews;

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

    public void updateFrom(Book other) {
        this.available = other.available;
    }

    @Override
    public String toString() {
        return String.format("\nTile: %s Author: %s, Year: %s, Test Id: %s, Available: %s\n",
                title, author, year, bookId, available);
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
