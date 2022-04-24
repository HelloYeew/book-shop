package Entity;
import Dao.UserDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Class represent user in the "users" database table.
 */
@DatabaseTable(tableName = "users", daoClass = UserDao.class)
public class User {
    /**
     * User's ID in database. It is autoincremented.
     */
    @DatabaseField(generatedId=true)
    private int id;

    /**
     * User's username represent the name of the user.
     */
    @DatabaseField(canBeNull = false)
    private String username;

    /**
     * Create a new user
     * @param username User's username represent the name of the user.
     */
    public User(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
