package sandkev.server.dao;

import org.springframework.jdbc.core.RowMapper;
import sandkev.shared.dao.GenericDaoAbstractSpringJdbc;
import sandkev.shared.domain.Loan;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kevin on 09/05/2015.
 */
public class LoanDaoImpl extends GenericDaoAbstractSpringJdbc<Loan, Long> implements LoanDao {

    private final TitleItemDao titleItemDao;
    private final BorrowerDao borrowerDao;

    public LoanDaoImpl(DataSource dataSource){
        super(dataSource,
                "select " +
                        "l.*" +
                " from public.loan l",
                "loan_id"
                );
        titleItemDao = new TitleItemDaoImpl(dataSource);
        borrowerDao = new BorrowerDaoImpl(dataSource);
    }

    @Override
    protected RowMapper<Loan> createRowMapper() {
        return new RowMapper<Loan>() {
            @Override
            public Loan mapRow(ResultSet rs, int i) throws SQLException {
                return new Loan(
                        rs.getLong("loan_id"),
                        borrowerDao.findById(rs.getLong("borrower_id")),
                        titleItemDao.findById(rs.getLong("title_item_id")),
                        rs.getInt("taken_out"),
                        rs.getInt("due_back")
                );
            }
        };
    }

    @Override
    public void save(Loan entity) {
        if(entity.getId() == -1){
            entity.setId(nextId());

            jdbcTemplate.update(
                    dialectFriendlySql("insert into loan (loan_id, title_item_id, borrower_id, taken_out, due_back) values( ?, ?, ?, ?, ?)")
                    ,entity.getId()
                    ,entity.getTitleItem().getId()
                    ,entity.getBorrower().getId()
                    ,entity.getTakenOut()
                    ,entity.getDueBack()
            );
        }
    }

    private Long nextId() {
        return jdbcTemplate.queryForLong(dialectFriendlySql("select loan_seq.nextval from dual"));
    }

}
