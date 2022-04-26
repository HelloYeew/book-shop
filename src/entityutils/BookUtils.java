package entityutils;

import entity.Book;

import java.util.List;

public class BookUtils {
    public static String[] readableColumnName = {"ID", "Title", "Author", "Genre", "Subgenre", "Pages", "Publisher", "Price"};

    public static String[] queryColumnName = {"id", "title", "author", "genre", "subgenre", "height", "publisher", "price"};

    public static Object[][] convertToArray(List<Book> books) {
        Object[][] data = new Object[books.size()][];
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            data[i] = new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getSubgenre(), book.getHeight(), book.getPublisher(), book.getPrice()};
        }
        return data;
    }

    public static Object[][] convertToArray(Book book) {
        Object[][] data = new Object[1][];
        data[0] = new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getSubgenre(), book.getHeight(), book.getPublisher(), book.getPrice()};
        return data;
    }
}
