package resource;

import core.book.Book;
import core.bookmedium.EBookReader;
import dao.EBookReaderDAO;
import io.dropwizard.hibernate.UnitOfWork;
import operations.ebookreader.AddNewEBookReader;
import operations.ebookreader.DeleteEBookReader;
import operations.ebookreader.GetAllEBookReaders;
import operations.ebookreader.GetEBookReaderById;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
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

    @PermitAll
    @GET
    @UnitOfWork
    public List<EBookReader> getEBookReaders() {
        return new GetAllEBookReaders(eBookReaderDAO).execute();
    }

    @RolesAllowed("ADMIN")
    @POST
    @UnitOfWork
    public EBookReader createEBookReader(EBookReader eBookReader) {
        return new AddNewEBookReader(eBookReaderDAO).execute(eBookReader);
    }

    @PermitAll
    @Path("{readerId}")
    @GET
    @UnitOfWork
    public EBookReader getReaderById(@PathParam("readerId") Long readerId) {
        return new GetEBookReaderById(eBookReaderDAO).execute(readerId);
    }

    @RolesAllowed("ADMIN")
    @Path("{readerId}")
    @DELETE
    @UnitOfWork
    public EBookReader deleteEBookReader(@PathParam("readerId")Long readerId) {
        return new DeleteEBookReader(eBookReaderDAO).execute(readerId);
    }
}


