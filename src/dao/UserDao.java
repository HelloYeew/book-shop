package dao;

import entity.User;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import entityutils.UserUtils;

import java.sql.SQLException;
import java.util.List;

public class UserDao extends BaseDaoImpl<User, Integer> {
    public UserDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, User.class);
    }

    /**
     * Return 2D array that can be render to table in GUI.
     *
     * @return 2D array that can be render to table in GUI.
     * @throws SQLException if SQL query failed.
     */
    public Object[][] getAllAsArray() throws SQLException {
        return UserUtils.convertToArray(queryForAll());
    }
}
