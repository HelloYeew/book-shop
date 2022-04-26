package entity;

import dao.UserDao;
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
     * Create a new user without ID.
     * @param username User's username represent the name of the user.
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * Create a new user with ID.
     * @param ID User's ID
     * @param username User's username represent the name of the user.
     */
    public User(int ID, String username) {
        this.id = ID;
        this.username = username;
    }

    /**
     * No argument constructor for User since it is required by ORMLite.
     */
    public User() {

    }

    /**
     * Get ID of the user.
     * @return ID of the user.
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID of the user.
     * @param id ID of the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get username of the user.
     * @return username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username of the user.
     * @param username username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the string representation of the user.
     * @return the string representation of the user.
     */
    public String toString() {
        return this.id + "." + username;
    }
}
