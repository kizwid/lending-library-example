package sandkev.server.dao;

import sandkev.shared.dao.GenericDao;
import sandkev.shared.domain.Title;
import sandkev.shared.domain.TitleItem;

import java.util.List;

/**
 * Created by kevin on 09/05/2015.
 */
public interface TitleDao extends GenericDao<Title, Long> {
    Title findByNameType(String name, String type);
    List<Title> findAvailable();
}
