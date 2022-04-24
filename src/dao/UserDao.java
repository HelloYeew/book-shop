package dao;

import entity.History;
import entity.User;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class UserDao extends BaseDaoImpl<User, Integer> implements DaoInstance {
    public UserDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, User.class);
    }


    /**
     * Return 2D array that can be render to table in GUI.
     *
     * @return 2D array that can be render to table in GUI.
     * @throws SQLException if SQL query failed.
     */
    @Override
    public Object[][] getAllAsArray() throws SQLException {
        List<User> users = this.queryForAll();
        Object[][] data = new Object[users.size()][];
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            data[i] = new Object[]{user.getId(), user.getUsername()};
        }
        return data;
    }
}
