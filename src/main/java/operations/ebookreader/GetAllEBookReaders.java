package operations.ebookreader;

import core.bookmedium.EBookReader;
import dao.EBookReaderDAO;

import java.util.List;

/**
 * Created by ivsi on 2/2/2016.
 */
public class GetAllEBookReaders {

    private final EBookReaderDAO eBookReaderDAO;

    public GetAllEBookReaders(EBookReaderDAO eBookReaderDAO) {
        this.eBookReaderDAO = eBookReaderDAO;
    }

    public List<EBookReader> execute(){
        return eBookReaderDAO.findAll();
    }
}
