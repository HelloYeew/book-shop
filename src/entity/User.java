package entity;
import dao.UserDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * Class represent user in the "users" database table.
 */
@DatabaseTable(tableName = "users", daoClass = UserDao.class)
public class User implements Entity {
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

    public static String[] readableColumnName = {"ID", "Username"};

    /**
     * Create a new user
     * @param username User's username represent the name of the user.
     */
    public User(String username) {
        this.username = username;
    }

    public User() {

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

    public String toString() {
        return this.id + username;
    }

    public static Object[][] convertToArray(List<User> users) {
        Object[][] data = new Object[users.size()][];
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            data[i] = new Object[]{user.getId(), user.getUsername()};
        }
        return data;
    }

    public static Object[][] convertToArray(User user) {
        Object[][] data = new Object[1][];
        data[0] = new Object[]{user.getId(), user.getUsername()};
        return data;
    }
}
