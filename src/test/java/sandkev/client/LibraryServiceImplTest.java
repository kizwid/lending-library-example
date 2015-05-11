package sandkev.client;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sandkev.AbstractDatabaseTest;
import sandkev.server.dao.TitleDao;
import sandkev.server.dao.TitleDaoImpl;
import sandkev.shared.domain.Borrower;
import sandkev.shared.domain.Loan;
import sandkev.shared.domain.Title;
import sandkev.shared.domain.TitleItem;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kevin on 11/05/2015.
 */
public class LibraryServiceImplTest extends AbstractDatabaseTest{

    private static final Logger logger = LoggerFactory.getLogger(LibraryServiceImplTest.class);

    private LibraryService libraryService;
    private TitleDao titleDao;

    @Before
    public void before(){

        libraryService = new LibraryServiceImpl(dataSource);
        titleDao = new TitleDaoImpl(dataSource);

    }

    @Test public void canAddItemsToInventory(){

        String csv =
                "7,DVD,Pi\n" +
                "1,Book,The Art Of Computer Programming Volumes 1-6\n" +
                "2,Book,The Pragmatic Programmer\n" +
                "6,Movie,Hackers\n" +
                "3,Book,Java Concurrency In Practice\n" +
                "4,Book,Introduction to Algorithms\n" +
                "7,DVD,Pi\n" +
                "4,Book,Introduction to Algorithms\n" +
                "5,DVD,The Imitation Game\n" +
                "5,VHS,WarGames\n" +
                "6,VHS,Hackers\n" +
                "4,Book,Introduction to Algorithms\n" +
                "7,DVD,Pi";
        for (String row : csv.split("\n")) {
            String[] columns = row.split(",");
            TitleItem titleItem = libraryService.addTitleItem(columns[2], columns[1]);
            logger.info("added item {}", titleItem);
        }

        List<Title> currentInventory = libraryService.getCurrentInventory();
        assertEquals(9, currentInventory.size());

        showInventory(currentInventory);


        Borrower kevin = libraryService.registerBorrower("Kevin");
        Borrower tom = libraryService.registerBorrower("Tom");
        Borrower peter = libraryService.registerBorrower("Peter");

        //there are 3 copies of Pi on DVD
        Loan kevinPiLoan = libraryService.lend(kevin, titleDao.findByNameType("Pi", "DVD"));
        Loan tomPiLoan = libraryService.lend(tom, titleDao.findByNameType("Pi", "DVD"));
        assertEquals(9, libraryService.getCurrentInventory().size());

        //this is the last copy of Pi
        Loan peterPiLoan = libraryService.lend(peter, titleDao.findByNameType("Pi", "DVD"));
        assertEquals(8, libraryService.getCurrentInventory().size());


        Loan tomWarGamesLoan = libraryService.lend(tom, titleDao.findByNameType("WarGames", "VHS"));
        assertEquals(7, libraryService.getCurrentInventory().size());

        libraryService.returnItem(kevinPiLoan.getTitleItem());
        assertEquals(8, libraryService.getCurrentInventory().size());

    }

    private void showInventory(List<Title> currentInventory) {
        System.out.println("current inventory: ");
        for (Title title : currentInventory) {
            System.out.println(title);
        }
    }


}