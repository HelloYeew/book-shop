package dao;

import entity.Book;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * DAO for Book entity by extending BaseDaoImpl from ORMLite.
 *
 * See {@link Book} for more information on this entity.
 */
public class BookDao extends BaseDaoImpl<Book, Integer> {
    /**
     * Constructor that takes connection source and creates DAO.
     * @param connectionSource Database connection source.
     * @throws SQLException if failed to connect to database.
     */
    public BookDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Book.class);
    }
}
