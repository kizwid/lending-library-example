package sandkev.server.dao;

import org.springframework.jdbc.core.RowMapper;
import sandkev.shared.dao.GenericDaoAbstractSpringJdbc;
import sandkev.shared.domain.Borrower;
import sandkev.shared.domain.Title;
import sandkev.shared.domain.TitleType;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kevin on 09/05/2015.
 */
public class BorrowerDaoImpl extends GenericDaoAbstractSpringJdbc<Borrower, Long> implements BorrowerDao {

    public BorrowerDaoImpl(DataSource dataSource){
        super(dataSource,
                "select " +
                        "b.*" +
                " from public.borrower b",
                "borrower_id"
                );
    }

    @Override
    protected RowMapper<Borrower> createRowMapper() {
        return new RowMapper<Borrower>() {
            @Override
            public Borrower mapRow(ResultSet rs, int i) throws SQLException {
                return new Borrower(
                         rs.getLong("borrower_id")
                        ,rs.getString("borrower_name")
                );
            }
        };
    }

    @Override
    public void save(Borrower entity) {
        if(entity.getId() == -1){
            Borrower existing = findByName(entity.getName());
            if(existing!=null){
                entity.setId(existing.getId());
            }else {
                entity.setId(nextId());

                jdbcTemplate.update(
                        dialectFriendlySql("insert into borrower (borrower_id, borrower_name) values( ?, ?)")
                        ,entity.getId()
                        ,entity.getName()
                );
            }
        }
    }

    private Long nextId() {
        return jdbcTemplate.queryForLong(dialectFriendlySql("select borrower_seq.nextval from dual"));
    }

    @Override
    public Borrower findByName(String name) {
        String sql = "select borrower_id" +
                " from public.borrower where borrower_name = ? ";
        try {
            long id = jdbcTemplate.queryForObject(sql,
                    new Object[]{name},
                    Long.class);
            return findById(id);
        }catch (Exception ignore){

        }
        return null;
    }
}
