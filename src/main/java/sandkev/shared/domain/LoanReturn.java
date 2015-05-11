package sandkev.shared.domain;

import sandkev.shared.dao.Identifiable;

/**
 * Created by kevin on 11/05/2015.
 */
public class LoanReturn implements Identifiable<Long>{
    private long id;
    private final long loanId;

    public LoanReturn(long loanId) {
        this(-1, loanId);
    }
    public LoanReturn(long id, long loanId) {
        this.id = id;
        this.loanId = loanId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public long getLoanId() {
        return loanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoanReturn that = (LoanReturn) o;

        if (id != that.id) return false;
        return loanId == that.loanId;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (loanId ^ (loanId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "LoanReturn{" +
                "id=" + id +
                ", loanId=" + loanId +
                '}';
    }
}
