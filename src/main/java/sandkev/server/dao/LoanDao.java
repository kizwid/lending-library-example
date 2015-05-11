package sandkev.server.dao;

import sandkev.shared.dao.GenericDao;
import sandkev.shared.domain.Loan;
import sandkev.shared.domain.TitleItem;

import java.util.List;

/**
 * Created by kevin on 09/05/2015.
 */
public interface LoanDao extends GenericDao<Loan, Long> {
    List<Loan> findOverDue();
    Loan findByItem(TitleItem titleItem);
}
