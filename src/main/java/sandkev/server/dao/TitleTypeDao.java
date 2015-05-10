package sandkev.server.dao;

import sandkev.shared.dao.GenericDao;
import sandkev.shared.domain.Title;
import sandkev.shared.domain.TitleType;

/**
 * Created by kevin on 09/05/2015.
 */
public interface TitleTypeDao extends GenericDao<TitleType, Integer> {
    TitleType findByName(String name);
}
