package core;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by ivsi on 1/4/2016.
 */
@NamedQueries(
        @NamedQuery(name = "BookReview.findByBookId", query = "From BookReview br where br.book.bookId = :bookId")
)
@Entity
public class BookReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookReviewId;
    private String title;
    private String text;

    @ManyToOne
    @JoinColumn(name = "bookId")
    @JsonBackReference
    private Book book;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "renteeId")
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

    @Override
    public String toString() {
        return String.format("\nRentee: %s\n Book: %s\n Title: %s\n Text: %s\n", book, title, text);
    }
}
