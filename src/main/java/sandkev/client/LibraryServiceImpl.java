package sandkev.client;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import sandkev.server.dao.*;
import sandkev.server.util.FormatUtil;
import sandkev.shared.domain.*;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

/**
 * Created by kevin on 10/05/2015.
 */
public class LibraryServiceImpl implements LibraryService {

    private final TitleDao titleDao;
    private final TitleItemDao titleItemDao;
    private final BorrowerDao borrowerDao;
    private final LoanDao loanDao;
    private final LoanReturnDao loanReturnDao;

    public LibraryServiceImpl(DataSource dataSource){
        titleDao = new TitleDaoImpl(dataSource);
        titleItemDao = new TitleItemDaoImpl(dataSource);
        borrowerDao = new BorrowerDaoImpl(dataSource);
        loanDao = new LoanDaoImpl(dataSource);
        loanReturnDao = new LoanReturnDaoImpl(dataSource);
    }

    @Override
    public List<Title> getCurrentInventory() {
        return titleDao.findAvailable();
    }

    @Override
    public List<Loan> getOverdueLoans() {
        return loanDao.findOverDue();
    }

    @Override
    public Loan lend(Borrower borrower, Title title) {
        DateTime today = new DateTime();
        MutableDateTime dueDate = new MutableDateTime(today);
        dueDate.addDays(7);
        int takenOut = FormatUtil.date2int(today.toDate());
        int dueBack = FormatUtil.date2int(dueDate.toDate());
        Loan loan = new Loan(borrower, titleItemDao.availableItem(title), takenOut, dueBack);
        loanDao.save(loan);
        return loan;
    }

    @Override
    public void returnItem(TitleItem titleItem) {
        Loan loan = loanDao.findByItem(titleItem);
        LoanReturn loanReturn = new LoanReturn(loan.getId());
        loanReturnDao.save(loanReturn);
        loan.setLoanReturnId(loanReturn.getId());
        loanDao.save(loan);
    }

    @Override
    public void returnItem(String borrowerName, String title, String titleType) {

    }

    @Override
    public Borrower registerBorrower(String name) {
        Borrower borrower = new Borrower(name);
        borrowerDao.save(borrower);
        return borrower;
    }

    @Override
    public TitleItem addTitleItem(String titleName, String type) {
        System.out.println("adding: " + titleName + " " + type);
        Title title = new Title(titleName, type);
        titleDao.save(title);
        TitleItem titleItem = new TitleItem(title);
        titleItemDao.save(titleItem);
        return titleItem;
    }
}
