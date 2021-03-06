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
     * DAO for the user entity.
     */
    private UserDao userDao;

    /**
     * DAO for the book entity.
     */
    private BookDao bookDao;

    /**
     * DAO for the history entity.
     */
    private HistoryDao historyDao;

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

        try {
            userDao = new UserDao(connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            bookDao = new BookDao(connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            historyDao = new HistoryDao(connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return the generated userDao
     * @return the generated UserDao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * Return the generated bookDao
     * @return the generated BookDao
     */
    public BookDao getBookDao() {
        return bookDao;
    }

    /**
     * Return the generated historyDao
     * @return the generated HistoryDao
     */
    public HistoryDao getHistoryDao() {
        return historyDao;
    }
}
