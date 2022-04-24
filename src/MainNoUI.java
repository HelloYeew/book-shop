import dao.BookDao;
import dao.DaoFactory;
import entity.Book;

import java.sql.SQLException;

public class MainNoUI {
    public static void main(String[] args) {
        String databaseUrl = "jdbc:sqlite:bookshop.db";

        DaoFactory daoFactory = null;
        try {
            daoFactory = new DaoFactory(databaseUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        BookDao bookDao = null;
        try {
            bookDao = new BookDao(daoFactory.getConnectionSource());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // print all books
        try {
            for (Book book : bookDao.getAll()) {
                System.out.println(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // add a book
        Book new_book = new Book("Among Us", "Yeew", "Fun", "Fiction", 200, "Sus publishing", 10.22);
        try {
            bookDao.addBook(new_book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            for (Book book : bookDao.getAll()) {
                System.out.println(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}