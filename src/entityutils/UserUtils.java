package entityutils;

import entity.User;

import java.util.List;

public class UserUtils {
    public static String[] readableColumnName = {"ID", "Username"};

    public static String[] queryColumnName = {"id", "username"};

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
