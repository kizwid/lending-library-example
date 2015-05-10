package sandkev.server.dao;

import sandkev.shared.dao.GenericDao;
import sandkev.shared.domain.Title;

/**
 * Created by kevin on 09/05/2015.
 */
public interface TitleDao extends GenericDao<Title, Long> {
    Title findByNameType(String name, String type);
}
