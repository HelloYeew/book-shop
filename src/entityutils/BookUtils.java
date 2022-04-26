package entityutils;

import entity.Book;

import java.util.List;

/**
 * Utility class for Book entity.
 *
 * See {@link Book} for more information on the entity.
 */
public class BookUtils {
    /**
     * Column name of the book table that can be used in UI.
     */
    public static String[] readableColumnName = {"ID", "Title", "Author", "Genre", "Subgenre", "Pages", "Publisher", "Price"};

    /**
     * Real column of the book table that can be used in database.
     */
    public static String[] queryColumnName = {"id", "title", "author", "genre", "subgenre", "height", "publisher", "price"};

    /**
     * Convert a list of Book that may be obtained from database query to an Object[][] that can be used in table UI.
     * @param books List of Book that may be obtained from database query.
     * @return Object[][] that can be used in table UI.
     */
    public static Object[][] convertToArray(List<Book> books) {
        Object[][] data = new Object[books.size()][];
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            data[i] = new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getSubgenre(), book.getHeight(), book.getPublisher(), book.getPrice()};
        }
        return data;
    }

    /**
     * Convert one book object to an Object[][] that can be used in table UI in case of one book.
     * @param book Book object that may be gotten only one book from database query.
     * @return Object[][] that can be used in table UI.
     */
    public static Object[][] convertToArray(Book book) {
        Object[][] data = new Object[1][];
        data[0] = new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getSubgenre(), book.getHeight(), book.getPublisher(), book.getPrice()};
        return data;
    }
}
