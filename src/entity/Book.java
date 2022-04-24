package entity;
import dao.BookDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * Class represent a book in "books" database table.
 */
@DatabaseTable(tableName = "books", daoClass = BookDao.class)
public class Book implements Entity {
    /**
     * Book ID in database. It must autoincrement.
     */
    @DatabaseField(generatedId=true)
    private int id;

    /**
     * Book title.
     */
    @DatabaseField(canBeNull = false)
    private String title;

    /**
     * Book author.
     */
    @DatabaseField(canBeNull = false)
    private String author;

    /**
     * Book genre.
     */
    @DatabaseField(canBeNull = false)
    private String genre;

    /**
     * Book subgenre.
     */
    @DatabaseField(canBeNull = false)
    private String subgenre;

    /**
     * Book's total number of pages.
     */
    @DatabaseField(canBeNull = false)
    private int height;

    /**
     * Book publisher.
     */
    @DatabaseField
    private String publisher;

    /**
     * Book price.
     */
    @DatabaseField(canBeNull = false)
    private double price;

    public static String[] readableColumnName = {"ID", "Title", "Author", "Genre", "Subgenre", "Pages", "Publisher", "Price"};

    public static String[] queryColumnName = {"id", "title", "author", "genre", "subgenre", "height", "publisher", "price"};

    /**
     * Create a new book.
     * @param title Book title.
     * @param author Book author.
     * @param genre Book genre.
     * @param subgenre Book subgenre.
     * @param height Book's total number of pages.
     * @param publisher Book publisher.
     * @param price Book price.
     */
    public Book(String title, String author, String genre, String subgenre, int height, String publisher, double price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.subgenre = subgenre;
        this.height = height;
        this.publisher = publisher;
        this.price = price;
    }

    public Book() {

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubgenre() {
        return subgenre;
    }

    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "id=" + id + ", title=" + title + ", author=" + author + ", genre=" + genre + ", subgenre=" + subgenre + ", height=" + height + ", publisher=" + publisher + ", price=" + price;
    }

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
