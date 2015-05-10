package sandkev.server.dao;

import org.junit.Test;
import sandkev.AbstractDatabaseTest;
import sandkev.shared.domain.Title;
import sandkev.shared.domain.TitleType;

import static org.junit.Assert.*;

/**
 * Created by kevin on 10/05/2015.
 */
public class TitleDaoImplTest extends AbstractDatabaseTest{

    @Test
    public void canSaveAndRetrieve(){

        TitleDao dao = new TitleDaoImpl(dataSource);
        Title title = new Title("War Games", "BOOK");
        dao.save(title);

        Title checkById = dao.findById(title.getId());
        assertEquals(title, checkById);

        Title checkByName = dao.findByNameType(title.getName(), title.getType());
        assertEquals(title, checkByName);

    }

}