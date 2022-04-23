package Table;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Class represent user in the "users" database table.
 */
@DatabaseTable(tableName = "users")
public class User {
    /**
     * User's ID in database. Use as primary key in database, and it must be generated automatically.
     */
    @DatabaseField(id = true)
    private int id;

    /**
     * User's username represent the name of the user.
     */
    @DatabaseField(canBeNull = false)
    private String username;

    // TODO: Getter and setter
}
