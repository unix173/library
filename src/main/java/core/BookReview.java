package core;

import javax.persistence.*;

/**
 * Created by ivsi on 1/25/2016.
 **/
@Entity
public class BookReview {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String text;
    @ManyToOne
    @JoinColumn(name = "renteeId")
    private Rentee rentee;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

    public BookReview() {
    }

    public BookReview(String title, String text, Rentee rentee, Book book) {
        this.title = title;
        this.text = text;
        this.rentee = rentee;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rentee getRentee() {
        return rentee;
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

    @Override
    public String toString() {
        return String.format("\nRentee: %s\n Book: %s\n Title: %s\n Text: %s\n", rentee, book, title, text);
    }
}
