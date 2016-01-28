package dao;

import core.Book;
import core.BookReview;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by ivsi on 1/26/2016.
 */
public class BookReviewDAO extends AbstractDAO<BookReview> {
    public BookReviewDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public BookReview create(BookReview bookReview) {
        return persist(bookReview);
    }

    public BookReview findById(Long bookReviewId) {
        return get(bookReviewId);
    }

    public List<BookReview> findByBookId(Long bookId) {
        return list(namedQuery("BookReview.findByBookId").setParameter("bookId", bookId));
    }

    public BookReview delete(BookReview bookReviewId) {
        currentSession().delete(bookReviewId);
        return bookReviewId;
    }

    public BookReview update(BookReview bookReviewToUpdate) {
        return persist(bookReviewToUpdate);
    }
}
