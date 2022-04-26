package dao;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

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

    public UserDao getUserDao() throws SQLException {
        try {
            return new UserDao(connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public BookDao getBookDao() throws SQLException {
        try {
            return new BookDao(connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HistoryDao getHistoryDao() throws SQLException {
        try {
            return new HistoryDao(connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
