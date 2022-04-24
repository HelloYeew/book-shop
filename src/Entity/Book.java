package Entity;
import Dao.BookDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Class represent a book in "books" database table.
 */
@DatabaseTable(tableName = "books", daoClass = BookDao.class)
public class Book {
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
}
