package sandkev.server.dao;

import sandkev.shared.dao.GenericDao;
import sandkev.shared.domain.Borrower;
import sandkev.shared.domain.Title;

/**
 * Created by kevin on 09/05/2015.
 */
public interface BorrowerDao extends GenericDao<Borrower, Long> {
    Borrower findByName(String name);
}
