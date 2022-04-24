package gui;

import dao.BookDao;
import dao.DaoFactory;
import entity.Book;
import entity.User;

import javax.swing.*;
import java.sql.SQLException;

public class MainGui extends JFrame {
    static String databaseUrl = "jdbc:sqlite:bookshop.db";
    private static DaoFactory daoFactory;

    public MainGui() {
        super("Book shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);

        showAllUsers();

        pack();
    }

    public void showAllBooks() {
        try {
            Object[][] data = daoFactory.getDaoInstance("Book").getAllAsArray();
            String[] columnNames = {"ID", "Title", "Author", "Genre", "Subgenre", "Pages", "Publisher", "Price"};
            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showAllUsers() {
        try {
            Object[][] data = daoFactory.getDaoInstance("User").getAllAsArray();
            String[] columnNames = {"ID", "Username"};
            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    public static void main(String[] args) {
        System.out.println("Starting application...");
        System.out.println("Connecting to database...");
        try {
            daoFactory = new DaoFactory(databaseUrl);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("Error on database connection\n" + e.getMessage());
            System.exit(1);
        }
        new MainGui();
    }
}
