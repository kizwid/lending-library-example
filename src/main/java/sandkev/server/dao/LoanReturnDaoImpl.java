package sandkev.server.dao;

import org.springframework.jdbc.core.RowMapper;
import sandkev.server.util.FormatUtil;
import sandkev.shared.dao.GenericDaoAbstractSpringJdbc;
import sandkev.shared.domain.Loan;
import sandkev.shared.domain.LoanReturn;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by kevin on 09/05/2015.
 */
public class LoanReturnDaoImpl extends GenericDaoAbstractSpringJdbc<LoanReturn, Long> implements LoanReturnDao {

    public LoanReturnDaoImpl(DataSource dataSource){
        super(dataSource,
                "select " +
                        "lr.*" +
                " from public.loan_return lr",
                "loan_return_id"
                );
    }

    @Override
    protected RowMapper<LoanReturn> createRowMapper() {
        return new RowMapper<LoanReturn>() {
            @Override
            public LoanReturn mapRow(ResultSet rs, int i) throws SQLException {
                return new LoanReturn(
                         rs.getLong("loan_return_id")
                        ,rs.getLong("loan_id")
                );
            }
        };
    }

    @Override
    public void save(LoanReturn entity) {
        if(entity.getId() == -1){
            entity.setId(nextId());

            jdbcTemplate.update(
                    dialectFriendlySql("insert into loan_return (loan_return_id, loan_id) values( ?, ?)")
                    ,entity.getId()
                    ,entity.getLoanId()
            );
        }
    }

    private Long nextId() {
        return jdbcTemplate.queryForLong(dialectFriendlySql("select loan_return_seq.nextval from dual"));
    }


}
