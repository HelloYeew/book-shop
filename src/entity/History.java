package entity;

import dao.HistoryDao;
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
     * Create a new history without ID.
     * @param buyer ID of buyer that is foreign key to user table.
     * @param book ID of book that is foreign key to book table.
     */
    public History(User buyer, Book book) {
        this.buyer = buyer;
        this.book = book;
    }

    /**
     * Create a new history with ID.
     * @param ID
     * @param buyer ID of buyer that is foreign key to user table.
     * @param book ID of book that is foreign key to book table.
     */
    public History(int ID, User buyer, Book book) {
        this.id = ID;
        this.buyer = buyer;
        this.book = book;
    }

    /**
     * No argument constructor for History since it is used in ORMLite.
     */
    public History() {

    }

    /**
     * Get ID of history.
     * @return ID of history.
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID of history.
     * @param id ID of history that need to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get ID of buyer.
     *
     * Buyer is foreign key to user table.
     * @return ID of buyer.
     */
    public User getBuyer() {
        return buyer;
    }

    /**
     * Set ID of buyer.
     * @param buyer ID of buyer that is foreign key to user table that need to be set.
     */
    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    /**
     * Get ID of book.
     *
     * Book is foreign key to book table.
     * @return ID of book.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Set ID of book.
     * @param book ID of book that is foreign key to book table that need to be set.
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Get string representation of history.
     * @return String representation of history.
     */
    public String toString() {
        return "History: " + this.id + " " + this.buyer.getUsername() + " " + this.book.getTitle();
    }
}
