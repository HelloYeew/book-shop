package dao;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import entity.User;

import java.sql.SQLException;

/**
 * Class that manage the DAO to make the program not to create a new DAO for each request.
 */
public class DaoFactory {
    public JdbcConnectionSource connectionSource;

    public DaoFactory(String url) throws SQLException {
        try {
            connectionSource = new JdbcConnectionSource(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public JdbcConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public DaoInstance getDaoInstance(String entity) {
        switch (entity) {
            case "User":
                try {
                    return new UserDao(connectionSource);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            case "Book":
                try {
                    return new BookDao(connectionSource);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            case "History":
                try {
                    return new HistoryDao(connectionSource);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            default:
                throw new RuntimeException("Unknown entity");
        }
    }

    public void close() throws SQLException {
        try {
            connectionSource.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
