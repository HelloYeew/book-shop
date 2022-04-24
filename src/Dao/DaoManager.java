package Dao;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.sql.SQLException;

/**
 * Class that manage the DAO to make the program not to create a new DAO for each request.
 */
public class DaoManager {
    public JdbcConnectionSource connectionSource;

    public DaoManager(String url) throws SQLException {
        try {
            connectionSource = new JdbcConnectionSource(url);
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
