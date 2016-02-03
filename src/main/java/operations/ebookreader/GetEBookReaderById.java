package operations.ebookreader;

import core.book.EBook;
import core.bookmedium.EBookReader;
import dao.EBookReaderDAO;

import java.util.List;

/**
 * Created by ivsi on 2/3/2016.
 */
public class GetEBookReaderById {
    private final EBookReaderDAO eBookReaderDAO;

    public GetEBookReaderById(EBookReaderDAO eBookReaderDAO) {
        this.eBookReaderDAO = eBookReaderDAO;
    }

    public EBookReader execute(Long readerID){
        return eBookReaderDAO.findById(readerID);
    }
}
