package entity.utils;

import entity.User;

import java.util.List;

/**
 * Utility class for User entity.
 *
 * See {@link User} for more information on the entity.
 */
public class UserUtils {
    /**
     * Column name of the user table that can be used in UI.
     */
    public static String[] readableColumnName = {"ID", "Username"};

    /**
     * Real column of the user table that can be used in database.
     */
    public static String[] queryColumnName = {"id", "username"};

    /**
     * Convert a list of User that may be obtained from database query to an Object[][] that can be used in table UI.
     * @param users List of User that may be obtained from database query.
     * @return Object[][] that can be used in table UI.
     */
    public static Object[][] convertToArray(List<User> users) {
        Object[][] data = new Object[users.size()][];
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            data[i] = new Object[]{user.getId(), user.getUsername()};
        }
        return data;
    }
}
