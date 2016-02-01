import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableList;
import core.Book;
import dao.BookDAO;
import io.dropwizard.testing.junit.ResourceTestRule;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.assertj.core.internal.cglib.core.Local;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.runners.MockitoJUnitRunner;
import resource.BookResource;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


/**
 * Created by ivsi on 1/29/2016.
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class BookResourceTest {
    private static final BookDAO bookDAO = mock(BookDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new BookResource(bookDAO))
            .build();

    @Captor
    private ArgumentCaptor<Book> bookArgumentCaptor;
    private Book book;

    @Before
    public void setUp() {
        book = new Book();
        book.setBookId(-9000);
        book.setTitle("Book1");
        book.setAuthor("Author1");
    }

    @After
    public void tearDown() {
        reset(bookDAO);
    }

    @Test
    public void createBook() throws JsonProcessingException {
        when(bookDAO.create(any(Book.class))).thenReturn(book);
        final Response response = resources.client().target("/books")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(book, MediaType.APPLICATION_JSON_TYPE));
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(bookDAO).create(bookArgumentCaptor.capture());
        assertThat(bookArgumentCaptor.getValue()).isEqualTo(book);
    }

    @Test
    public void listBooks() throws Exception {
        final ImmutableList<Book> books = ImmutableList.of(book);
        when(bookDAO.findAll()).thenReturn(books);

        final List<Book> response = resources.client().target("/books")
                .request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Book>>() {
                });
        verify(bookDAO).findAll();
        assertThat(response).containsAll(books);
    }

}
