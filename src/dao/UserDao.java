package dao;

import entity.User;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * DAO for User entity by extending BaseDaoImpl from ORMLite.
 *
 * See {@link User} for more information on this entity.
 */
public class UserDao extends BaseDaoImpl<User, Integer> {
    /**
     * Constructor that takes connection source and creates DAO.
     * @param connectionSource Database connection source.
     * @throws SQLException if failed to connect to database.
     */
    public UserDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, User.class);
    }
}
