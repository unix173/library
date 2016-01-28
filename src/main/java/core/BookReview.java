package core;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by ivsi on 1/4/2016.
 */
@NamedQueries({
        @NamedQuery(name = "BookReview.findByBookId", query = "From BookReview br where br.book.bookId = :bookId")
})
@Entity
public class BookReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(required = false)
    @JsonIgnore
    private Long bookReviewId;

    private String title;
    private String text;

    @ManyToOne
    @JoinColumn(name = "bookId")
    @JsonBackReference(value = "bookRef")
    @JsonProperty(required = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "renteeId")
    @JsonBackReference(value = "renteeRef")
    @JsonProperty(required = false)
    private Rentee rentee;

    public BookReview() {
    }

    public BookReview(Book book, String title, String text, Rentee rentee) {
        this.book = book;
        this.title = title;
        this.text = text;
        this.rentee = rentee;
    }

    public Book getBook() {
        return book;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Long getBookReviewId() {
        return bookReviewId;
    }

    public void setBookReviewId(Long bookReviewId) {
        this.bookReviewId = bookReviewId;
    }

    public Rentee getRentee() {
        return rentee;
    }

    public void setRentee(Rentee rentee) {
        this.rentee = rentee;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("\nRentee: %s\n Test: %s\n Title: %s\n Text: %s\n", book, title, text);
    }
}
