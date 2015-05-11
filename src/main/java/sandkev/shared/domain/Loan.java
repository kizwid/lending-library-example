package sandkev.shared.domain;

import sandkev.shared.dao.Identifiable;

/**
 * Created by kevin on 10/05/2015.
 */
public class Loan implements Identifiable<Long>{
    private long id;
    private final Borrower borrower;
    private final TitleItem titleItem;
    private final int takenOut;
    private final int dueBack;
    private long loanReturnId;

    public Loan(Borrower borrower, TitleItem titleItem, int takenOut, int dueBack) {
        this(-1, borrower, titleItem, takenOut, dueBack, 0);
    }
    public Loan(long id, Borrower borrower, TitleItem titleItem, int takenOut, int dueBack, long loanReturnId) {
        this.id = id;
        this.borrower = borrower;
        this.titleItem = titleItem;
        this.takenOut = takenOut;
        this.dueBack = dueBack;
        this.loanReturnId = loanReturnId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public TitleItem getTitleItem() {
        return titleItem;
    }

    public int getTakenOut() {
        return takenOut;
    }

    public int getDueBack() {
        return dueBack;
    }

    public long getLoanReturnId() {
        return loanReturnId;
    }

    public void setLoanReturnId(long loanReturnId) {
        this.loanReturnId = loanReturnId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Loan loan = (Loan) o;

        if (id != loan.id) return false;
        if (takenOut != loan.takenOut) return false;
        if (dueBack != loan.dueBack) return false;
        if (loanReturnId != loan.loanReturnId) return false;
        if (borrower != null ? !borrower.equals(loan.borrower) : loan.borrower != null) return false;
        return !(titleItem != null ? !titleItem.equals(loan.titleItem) : loan.titleItem != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (borrower != null ? borrower.hashCode() : 0);
        result = 31 * result + (titleItem != null ? titleItem.hashCode() : 0);
        result = 31 * result + takenOut;
        result = 31 * result + dueBack;
        result = 31 * result + (int) (loanReturnId ^ (loanReturnId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", borrower=" + borrower +
                ", titleItem=" + titleItem +
                ", takenOut=" + takenOut +
                ", dueBack=" + dueBack +
                ", loanReturnId=" + loanReturnId +
                '}';
    }
}
