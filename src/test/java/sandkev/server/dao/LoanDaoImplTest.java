package sandkev.server.dao;

import org.junit.Test;
import sandkev.AbstractDatabaseTest;
import sandkev.shared.domain.Borrower;
import sandkev.shared.domain.Loan;
import sandkev.shared.domain.Title;
import sandkev.shared.domain.TitleItem;

import static org.junit.Assert.*;

/**
 * Created by kevin on 10/05/2015.
 */
public class LoanDaoImplTest extends AbstractDatabaseTest{

    @Test
    public void canSaveAndRetrieve(){

        TitleDao titleDao = new TitleDaoImpl(dataSource);
        Title title = new Title("War Games", "BOOK");
        titleDao.save(title);

        TitleItemDao titleItemDao = new TitleItemDaoImpl(dataSource);
        TitleItem titleItem = new TitleItem(title);
        titleItemDao.save(titleItem);

        BorrowerDao borrowerDao = new BorrowerDaoImpl(dataSource);
        Borrower borrower = new Borrower("Kevin");
        borrowerDao.save(borrower);

        LoanDao dao = new LoanDaoImpl(dataSource);
        Loan loan = new Loan(borrower, titleItem, 20150510, 20150515);
        dao.save(loan);

        Loan checkById = dao.findById(loan.getId());
        assertEquals(loan, checkById);

    }

}