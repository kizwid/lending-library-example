package sandkev.server.dao;

import org.springframework.jdbc.core.RowMapper;
import sandkev.shared.dao.GenericDaoAbstractSpringJdbc;
import sandkev.shared.domain.Title;
import sandkev.shared.domain.TitleType;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kevin on 09/05/2015.
 */
public class TitleDaoImpl extends GenericDaoAbstractSpringJdbc<Title, Long> implements TitleDao {

    private final TitleTypeDao titleTypeDao;

    public TitleDaoImpl(DataSource dataSource){
        super(dataSource,
                "select " +
                        "t.*" +
                        ",(select title_type_name from public.title_type x where x.title_type_id = t.title_type_id) title_type" +
                " from public.title t",
                "title_id"
                );
        titleTypeDao = new TitleTypeDaoImpl(dataSource);
    }

    @Override
    protected RowMapper<Title> createRowMapper() {
        return new RowMapper<Title>() {
            @Override
            public Title mapRow(ResultSet rs, int i) throws SQLException {
                return new Title(
                         rs.getLong("title_id")
                        ,rs.getString("title_name")
                        ,rs.getString("title_type")
                );
            }
        };
    }

    @Override
    public void save(Title entity) {
        if(entity.getId() == -1){
            Title existing = findByNameType(entity.getName(), entity.getType());
            if(existing!=null){
                entity.setId(existing.getId());
            }else {
                entity.setId(nextId());

                TitleType titleType = titleTypeDao.findByName(entity.getType());
                if(titleType==null){
                    titleType = new TitleType(entity.getType());
                    titleTypeDao.save(titleType);
                }

                jdbcTemplate.update(
                        dialectFriendlySql("insert into title (title_id, title_type_id, title_name) values( ?, ?, ?)")
                        ,entity.getId()
                        ,titleType.getId()
                        ,entity.getName()
                );
            }
        }
    }

    private Long nextId() {
        return jdbcTemplate.queryForLong(dialectFriendlySql("select title_seq.nextval from dual"));
    }

    @Override
    public Title findByNameType(String name, String type) {
        String sql = "select title_id" +
                " from public.title where title_name = ? " +
                " and title_type_id = (" +
                "  select title_type_id from public.title_type where title_type_name = ?" +
                ")";
        try {
            long id = jdbcTemplate.queryForObject(sql,
                    new Object[]{name, type},
                    Long.class);
            return findById(id);
        }catch (Exception ignore){

        }
        return null;
    }
}
