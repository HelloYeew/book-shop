package Table;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Class represent a book in "books" database table.
 */
@DatabaseTable(tableName = "books")
public class Book {
    @DatabaseField(id = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String title;

    @DatabaseField(canBeNull = false)
    private String author;

    @DatabaseField(canBeNull = false)
    private String genre;

    @DatabaseField(canBeNull = false)
    private String subgenre;

    @DatabaseField(canBeNull = false)
    private int height;

    @DatabaseField
    private String publisher;

    @DatabaseField(canBeNull = false)
    private double price;

    // TODO: Getter and setter
}
