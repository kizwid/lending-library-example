package sandkev.client;

import sandkev.server.dao.*;
import sandkev.shared.domain.Borrower;
import sandkev.shared.domain.Loan;
import sandkev.shared.domain.Title;
import sandkev.shared.domain.TitleItem;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by kevin on 10/05/2015.
 */
public class LibraryServiceImpl implements LibraryService {

    private final TitleDao titleDao;
    private final TitleItemDao titleItemDao;
    private final BorrowerDao borrowerDao;
    private final LoanDao loanDao;

    public LibraryServiceImpl(DataSource dataSource){
        titleDao = new TitleDaoImpl(dataSource);
        titleItemDao = new TitleItemDaoImpl(dataSource);
        borrowerDao = new BorrowerDaoImpl(dataSource);
        loanDao = new LoanDaoImpl(dataSource);
    }

    @Override
    public List<Title> getCurrentInventory() {
        return null;
    }

    @Override
    public List<Loan> getOverdueLoans() {
        return null;
    }

    @Override
    public Loan lend(Borrower borrower, Title title) {
        return null;
    }

    @Override
    public void returnItem(TitleItem titleItem) {

    }

    @Override
    public void returnItem(String borrowerName, String title, String titleType) {

    }

    @Override
    public Borrower registerBorrower(String name) {
        return null;
    }

    @Override
    public TitleItem addStock(String title, String type) {
        return null;
    }
}
