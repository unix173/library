package resource;

import core.bookmedium.EBookReader;
import dao.EBookReaderDAO;
import io.dropwizard.hibernate.UnitOfWork;
import operations.ebookreader.AddNewEBookReader;
import operations.ebookreader.GetAllEBookReaders;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by ivsi on 2/2/2016.
 */
@Path("/readers")
public class EBookReaderResource {

    private final EBookReaderDAO eBookReaderDAO;

    public EBookReaderResource(EBookReaderDAO eBookReaderDAO) {
        this.eBookReaderDAO = eBookReaderDAO;
    }

    @GET
    @UnitOfWork
    public List<EBookReader> getEBookReaders() {
        return new GetAllEBookReaders(eBookReaderDAO).execute();
    }

    @POST
    @UnitOfWork
    public EBookReader createEBookReader(EBookReader eBookReader) {
        return new AddNewEBookReader(eBookReaderDAO).execute(eBookReader);
    }

}
