package dao;

import entity.Book;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class BookDao extends BaseDaoImpl<Book, Integer> implements DaoInstance {
    public BookDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Book.class);
    }

    public void addBook(Book book) throws SQLException {
        create(book);
    }

    public List<Book> getAll() throws SQLException {
        return this.queryForAll();
    }

    public Object[][] getAllAsArray() throws SQLException {
        List<Book> books = this.queryForAll();
        Object[][] data = new Object[books.size()][];
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            data[i] = new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getSubgenre(), book.getHeight(), book.getPublisher(), book.getPrice()};
        }
        return data;
    }
}
