package operations.ebookreader;

import core.bookmedium.EBookReader;
import dao.EBookReaderDAO;

/**
 * Created by ivsi on 2/2/2016.
 */
public class AddNewEBookReader {

    private final EBookReaderDAO eBookReaderDAO;

    public AddNewEBookReader(EBookReaderDAO eBookReaderDAO) {
        this.eBookReaderDAO = eBookReaderDAO;
    }

    public EBookReader execute(EBookReader eBookReader) {
        return eBookReaderDAO.create(eBookReader);
    }
}
