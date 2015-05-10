package sandkev.server.dao;

import org.junit.Test;
import sandkev.AbstractDatabaseTest;
import sandkev.shared.domain.Borrower;

import static org.junit.Assert.assertEquals;

/**
 * Created by kevin on 10/05/2015.
 */
public class BorrowerDaoImplTest extends AbstractDatabaseTest{

    @Test
    public void canSaveAndRetrieve(){

        BorrowerDao dao = new BorrowerDaoImpl(dataSource);
        Borrower borrower = new Borrower("Kevin");
        dao.save(borrower);

        Borrower checkById = dao.findById(borrower.getId());
        assertEquals(borrower, checkById);

        Borrower checkByName = dao.findByName(borrower.getName());
        assertEquals(borrower, checkByName);

    }

}