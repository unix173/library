package operations.ebookreader;

import core.bookmedium.EBookReader;
import dao.EBookReaderDAO;

/**
 * Created by ivsi on 2/3/2016.
 */
public class DeleteEBookReader {
    private final EBookReaderDAO eBookReaderDAO;

    public DeleteEBookReader(EBookReaderDAO eBookReaderDAO) {
        this.eBookReaderDAO = eBookReaderDAO;
    }
    public EBookReader execute(Long rederId) {
        return eBookReaderDAO.delete(rederId);
    }
}
