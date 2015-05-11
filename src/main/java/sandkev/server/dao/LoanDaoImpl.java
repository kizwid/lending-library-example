package sandkev.server.dao;

import org.springframework.jdbc.core.RowMapper;
import sandkev.server.util.FormatUtil;
import sandkev.shared.dao.GenericDaoAbstractSpringJdbc;
import sandkev.shared.domain.Loan;
import sandkev.shared.domain.Title;
import sandkev.shared.domain.TitleItem;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
                        rs.getInt("due_back"),
                        rs.getLong("loan_return_id")
                );
            }
        };
    }

    @Override
    public void save(Loan entity) {
        if(entity.getId() == -1){
            entity.setId(nextId());

            jdbcTemplate.update(
                    dialectFriendlySql("insert into loan (loan_id, title_item_id, borrower_id, taken_out, due_back, loan_return_id) values( ?, ?, ?, ?, ?, ?)")
                    ,entity.getId()
                    ,entity.getTitleItem().getId()
                    ,entity.getBorrower().getId()
                    ,entity.getTakenOut()
                    ,entity.getDueBack()
                    ,entity.getLoanReturnId()
            );

        }else {

            jdbcTemplate.update(
                    dialectFriendlySql("update loan set loan_return_id = ? where loan_id = ?")
                    ,entity.getLoanReturnId()
                    ,entity.getId()
            );

        }
    }

    private Long nextId() {
        return jdbcTemplate.queryForLong(dialectFriendlySql("select loan_seq.nextval from dual"));
    }

    @Override
    public List<Loan> findOverDue() {
        int today = FormatUtil.date2int(new Date());
        String sql = "select * from loan where due_back > ?";
        try {
            return jdbcTemplate.query(dialectFriendlySql(sql), new Object[]{today},
                    createRowMapper());

        }catch (Exception ignore){

        }
        return Collections.emptyList();
    }

    @Override
    public Loan findByItem(TitleItem titleItem) {
        String sql = "select * from loan where loan_return_id = 0 and title_item_id = ?";
        try {
            return jdbcTemplate.query(dialectFriendlySql(sql), new Object[]{titleItem.getId()},
                    createRowMapper()).get(0);

        }catch (Exception ignore){

        }
        return null;
    }


}
