package dao;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.sql.SQLException;

/**
 * Class that act as a factory to create the different DAO
 */
public class DaoFactory {
    /**
     * Connection source from the database
     */
    public JdbcConnectionSource connectionSource;

    /**
     * Constructor of the factory that will create the connection to the database
     * @param url the url of the database
     */
    public DaoFactory(String url) throws SQLException {
        try {
            connectionSource = new JdbcConnectionSource(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate the UserDao from the connection source
     * @return the generated UserDao
     */
    public UserDao getUserDao() throws SQLException {
        try {
            return new UserDao(connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate the BookDao from the connection source
     * @return the generated BookDao
     */
    public BookDao getBookDao() throws SQLException {
        try {
            return new BookDao(connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate the HistoryDao from the connection source
     * @return the generated HistoryDao
     */
    public HistoryDao getHistoryDao() throws SQLException {
        try {
            return new HistoryDao(connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
