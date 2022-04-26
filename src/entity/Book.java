package entity;
import dao.BookDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

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
    @DatabaseField
    private String author;

    /**
     * Book genre.
     */
    @DatabaseField
    private String genre;

    /**
     * Book subgenre.
     */
    @DatabaseField
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
     * Create a new book without ID.
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

    /**
     * Create a new book with ID.
     * @param ID Book ID.
     * @param author Book author.
     * @param genre Book genre.
     * @param subgenre Book subgenre.
     * @param height Book's total number of pages.
     * @param publisher Book publisher.
     * @param price Book price.
     */
    public Book(int ID, String title, String author, String genre, String subgenre, int height, String publisher, double price) {
        this.id = ID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.subgenre = subgenre;
        this.height = height;
        this.publisher = publisher;
        this.price = price;
    }

    /**
     * No argument constructor for Book since it is used in ORMLite.
     */
    public Book() {

    }

    /**
     * Get book's ID.
     * @return Book's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Set book's ID.
     * @param id ID of the book that want to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get book's title.
     * @return Book's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set book's title.
     * @param title Title of the book that want to be set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get book's author.
     * @return Book's author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set book's author.
     * @param author Author of the book that want to be set.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Get book's genre.
     * @return Book's genre.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Set book's genre.
     * @param genre Genre of the book that want to be set.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Get book's subgenre.
     * @return Book's subgenre.
     */
    public String getSubgenre() {
        return subgenre;
    }

    /**
     * Set book's subgenre.
     * @param subgenre Subgenre of the book that want to be set.
     */
    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }

    /**
     * Get book's total number of pages.
     * @return Book's total number of pages.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set book's total number of pages.
     * @param height Total number of pages of the book that want to be set.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Get book's publisher.
     * @return Book's publisher.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Set book's publisher.
     * @param publisher Publisher of the book that want to be set.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Get book's price.
     * @return Book's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set book's price.
     * @param price Price of the book that want to be set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get string representation of the book.
     * @return String representation of the book.
     */
    public String toString() {
        return "(ID: " + id + ") " + title + " by " + author + " (" + genre + ")";
    }
}
