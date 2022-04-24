package Entity;
import Dao.HistoryDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Class represent history of the shop.
 */
@DatabaseTable(tableName = "history", daoClass = HistoryDao.class)
public class History {
    /**
     * History ID in database.
     */
    @DatabaseField(generatedId=true)
    private int id;

    /**
     * ID of buyer that is foreign key to user table.
     */
    @DatabaseField(foreign = true, columnName = "buyer_id")
    private User buyer;

    /**
     * ID of book that is foreign key to book table.
     */
    @DatabaseField(foreign = true, columnName = "book_id")
    private Book book;

    /**
     * Create a new history.
     * @param buyer ID of buyer that is foreign key to user table.
     * @param book ID of book that is foreign key to book table.
     */
    public History(User buyer, Book book) {
        this.buyer = buyer;
        this.book = book;
    }
}
