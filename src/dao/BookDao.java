package dao;

import entity.Book;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import entityutils.BookUtils;

import java.sql.SQLException;
import java.util.List;

public class BookDao extends BaseDaoImpl<Book, Integer> {
    public BookDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Book.class);
    }

    /**
     * Return 2D array that can be render to table in GUI.
     *
     * @return 2D array that can be render to table in GUI.
     * @throws SQLException if SQL query failed.
     */
    public Object[][] getAllAsArray() throws SQLException {
        return BookUtils.convertToArray(queryForAll());
    }
}
