package sandkev.server.dao;

import org.springframework.jdbc.core.RowMapper;
import sandkev.server.util.FormatUtil;
import sandkev.shared.dao.GenericDaoAbstractSpringJdbc;
import sandkev.shared.domain.Title;
import sandkev.shared.domain.TitleItem;
import sandkev.shared.domain.TitleType;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by kevin on 09/05/2015.
 */
public class TitleItemDaoImpl extends GenericDaoAbstractSpringJdbc<TitleItem, Long> implements TitleItemDao {

    private final TitleDao titleDao;

    public TitleItemDaoImpl(DataSource dataSource){
        super(dataSource,
                "select " +
                        "ti.*" +
                " from public.title_item ti",
                "title_item_id"
                );
        titleDao = new TitleDaoImpl(dataSource);
    }

    @Override
    protected RowMapper<TitleItem> createRowMapper() {
        return new RowMapper<TitleItem>() {
            @Override
            public TitleItem mapRow(ResultSet rs, int i) throws SQLException {
                return new TitleItem(
                         rs.getLong("title_item_id")
                        ,titleDao.findById(rs.getLong("title_id"))
                );
            }
        };
    }

    @Override
    public void save(TitleItem entity) {
        if(entity.getId() == -1){
            entity.setId(nextId());
            jdbcTemplate.update(
                    dialectFriendlySql("insert into title_item (title_item_id, title_id) values( ?, ?)")
                    ,entity.getId()
                    ,entity.getTitle().getId()
            );
        }
    }

    private Long nextId() {
        return jdbcTemplate.queryForLong(dialectFriendlySql("select title_item_seq.nextval from dual"));
    }

    @Override
    public TitleItem availableItem(Title title) {
        int today = FormatUtil.date2int(new Date());
        String sql = "select ti.* " +
                " from title_item ti " +
                " inner join title t on (t.title_id = ti.title_id)" +
                " inner join title_type tt on (tt.title_type_id = t.title_type_id)" +
                " left outer join loan l on (l.title_item_id = ti.title_item_id)" +
                " where " +
                " nvl(l.loan_return_id, 1) != 0" +
                " and t.title_name = ?" +
                " and tt.title_type_name = ?";
        try {
            return jdbcTemplate.query(dialectFriendlySql(sql), new Object[]{title.getName(), title.getType()},
                    createRowMapper()).get(0);

        }catch (Exception ignore){

        }
        return null;
    }
}
