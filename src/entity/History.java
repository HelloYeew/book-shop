package entity;
import com.j256.ormlite.field.ForeignCollectionField;
import dao.HistoryDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * Class represent history of the shop.
 */
@DatabaseTable(tableName = "history", daoClass = HistoryDao.class)
public class History implements Entity {
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

    public static String[] readableColumnName = {"ID", "Buyer ID", "Book ID"};

    public static String[] queryColumnName = {"id", "buyer_id", "book_id"};

    /**
     * Create a new history.
     * @param buyer ID of buyer that is foreign key to user table.
     * @param book ID of book that is foreign key to book table.
     */
    public History(User buyer, Book book) {
        this.buyer = buyer;
        this.book = book;
    }

    public History(int ID, User buyer, Book book) {
        this.id = ID;
        this.buyer = buyer;
        this.book = book;
    }

    public History() {

    }

    public int getId() {
        return id;
    }

    public User getBuyer() {
        return buyer;
    }

    public Book getBook() {
        return book;
    }

    public String toString() {
        return "History: " + this.id + " " + this.buyer.getUsername() + " " + this.book.getTitle();
    }

    public static Object[][] convertToArray(List<History> histories) {
        Object[][] data = new Object[histories.size()][];
        for (int i = 0; i < histories.size(); i++) {
            History history = histories.get(i);
            data[i] = new Object[]{history.getId(), history.getBuyer().getId(), history.getBook().getId()};
        }
        return data;
    }

    public static Object[][] convertToArray(History history) {
        Object[][] data = new Object[1][];
        data[0] = new Object[]{history.getId(), history.getBuyer().getId(), history.getBook().getId()};
        return data;
    }
}
