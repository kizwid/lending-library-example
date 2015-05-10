package sandkev.server.dao;

import org.junit.Test;
import sandkev.AbstractDatabaseTest;
import sandkev.shared.domain.TitleType;

import static org.junit.Assert.*;

/**
 * Created by kevin on 10/05/2015.
 */
public class TitleTypeDaoImplTest extends AbstractDatabaseTest{

    @Test public void canSaveAndRetrieve(){

        TitleTypeDao dao = new TitleTypeDaoImpl(dataSource);
        TitleType titleType = new TitleType("BOOK");
        dao.save(titleType);

        TitleType checkById = dao.findById(titleType.getId());
        assertEquals(titleType, checkById);

        TitleType checkByName = dao.findByName(titleType.getName());
        assertEquals(titleType, checkByName);



    }

}