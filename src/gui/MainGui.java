package gui;

import dao.DaoFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class MainGui extends JFrame {
    static String databaseUrl = "jdbc:sqlite:bookshop.db";
    private static DaoFactory daoFactory;

    private JTable mainTable;

    private JScrollPane mainScrollPane;

    private JLabel countLabel = new JLabel("Count : ");

    public MainGui() {
        super("Book shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JButton showAllBooksButton = new JButton("Show all books");
        showAllBooksButton.addActionListener(e -> showAllBooks());
        topPanel.add(showAllBooksButton);
        JButton showAllUserButton = new JButton("Show all users");
        showAllUserButton.addActionListener(e -> showAllUser());
        topPanel.add(showAllUserButton);
        JButton showAllHistoryButton = new JButton("Show all history");
        showAllHistoryButton.addActionListener(e -> showAllHistory());
        topPanel.add(showAllHistoryButton);
        add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        JLabel countLabel = new JLabel("Count : ");
        bottomPanel.add(countLabel);
        add(bottomPanel, BorderLayout.SOUTH);

        try {
            Object[][] data = daoFactory.getDaoInstance("Book").getAllAsArray();
            String[] columnNames = {"ID", "Title", "Author", "Genre", "Subgenre", "Pages", "Publisher", "Price"};
            countLabel.setText("Count : " + data.length);
            mainTable = new JTable(data, columnNames);
            mainTable.setDefaultEditor(Object.class, null);
            mainScrollPane = new JScrollPane(mainTable);
            add(mainScrollPane, BorderLayout.CENTER);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        add(mainScrollPane, BorderLayout.CENTER);

        pack();
    }

    private void showAllBooks() {
        try {
            Object[][] data = daoFactory.getDaoInstance("Book").getAllAsArray();
            String[] columnNames = {"ID", "Title", "Author", "Genre", "Subgenre", "Pages", "Publisher", "Price"};
            redrawTable(data, columnNames);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAllUser() {
        try {
            Object[][] data = daoFactory.getDaoInstance("User").getAllAsArray();
            String[] columnNames = {"ID", "Username"};
            redrawTable(data, columnNames);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAllHistory() {
        try {
            Object[][] data = daoFactory.getDaoInstance("History").getAllAsArray();
            String[] columnNames = {"ID", "Buyer Username", "Book Title"};
            redrawTable(data, columnNames);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void redrawTable(Object[][] data, String[] columnNames) {
        // update table
        mainTable.setModel(new DefaultTableModel(data, columnNames));
        mainScrollPane.setViewportView(mainTable);
        countLabel.setText("Count : " + data.length);
        // Update count label
        repaint();
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
