package sandkev.client;

import sandkev.shared.domain.Borrower;
import sandkev.shared.domain.Loan;
import sandkev.shared.domain.Title;
import sandkev.shared.domain.TitleItem;

import java.util.List;

/**
 * Created by kevin on 10/05/2015.
 */
public interface LibraryService {

    List<Title> getCurrentInventory();
    List<Loan> getOverdueLoans();
    Loan lend(Borrower borrower, Title title);
    void returnItem(TitleItem titleItem);
    void returnItem(String borrowerName, String title, String titleType);

    Borrower registerBorrower(String name);
    TitleItem addTitleItem(String title, String type);


}
