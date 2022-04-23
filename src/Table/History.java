package Table;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Class represent history of the shop.
 */
@DatabaseTable(tableName = "history")
public class History {
    @DatabaseField(id = true)
    private int id;

    @DatabaseField(foreign = true, columnName = "buyer_id")
    private User buyer;

    @DatabaseField(foreign = true, columnName = "book_id")
    private Book book;

    // TODO: Getter and setter
}
