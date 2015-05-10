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
public class TitleTypeDaoImpl extends GenericDaoAbstractSpringJdbc<TitleType, Integer> implements TitleTypeDao {

    public TitleTypeDaoImpl(DataSource dataSource){
        super(dataSource,
                "select " +
                        "tt.*" +
                " from public.title_type tt",
                "title_type_id"
                );
    }

    @Override
    protected RowMapper<TitleType> createRowMapper() {
        return new RowMapper<TitleType>() {
            @Override
            public TitleType mapRow(ResultSet rs, int i) throws SQLException {
                return new TitleType(
                         rs.getInt("title_type_id")
                        ,rs.getString("title_type_name")
                );
            }
        };
    }

    @Override
    public void save(TitleType entity) {
        if(entity.getId() == -1){
            TitleType existing = findByName(entity.getName().toUpperCase());
            if(existing!=null){
                entity.setId(existing.getId());
            }else {
                entity.setId(nextId());

                jdbcTemplate.update(
                        dialectFriendlySql("insert into title_type (title_type_id, title_type_name) values( ?, ?)")
                        ,entity.getId()
                        ,entity.getName().toUpperCase()
                );

            }
        }
    }

    private Integer nextId() {
        return jdbcTemplate.queryForInt(dialectFriendlySql("select title_type_seq.nextval from dual"));
    }

    @Override
    public TitleType findByName(String name) {
        String sql = "select title_type_id" +
                " from public.title_type where title_type_name = ? ";
        try {
            int id = jdbcTemplate.queryForObject(dialectFriendlySql(sql),
                    new Object[]{name.toUpperCase()},
                    Integer.class);
            return findById(id);
        }catch (Exception ignore){

        }
        return null;
    }
}
