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

    private GuiState guiState = GuiState.BOOKS;
    private JComboBox<String> searchComboBox;

    private JTextField searchTextField;

    public MainGui() {
        super("Book shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));

        JPanel buttonTopPanel = new JPanel();
        buttonTopPanel.setLayout(new FlowLayout());
        JButton showAllBooksButton = new JButton("Books");
        showAllBooksButton.addActionListener(e -> changeToBookState());
        buttonTopPanel.add(showAllBooksButton);
        JButton showAllUserButton = new JButton("Users");
        showAllUserButton.addActionListener(e -> showAllUser());
        buttonTopPanel.add(showAllUserButton);
        JButton showAllHistoryButton = new JButton("History");
        showAllHistoryButton.addActionListener(e -> showAllHistory());
        buttonTopPanel.add(showAllHistoryButton);
        topPanel.add(buttonTopPanel);
        // Add search text field below
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchTextField = new JTextField(20);
        // Set up search button
        String[] bookSearchFields = {"ID", "Title", "Author", "Genre", "Subgenre", "Pages", "Publisher", "Price"};
        searchComboBox = new JComboBox<>(bookSearchFields);
        searchPanel.add(searchComboBox);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        topPanel.add(searchPanel);
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
        setVisible(true);
    }

    private void changeToBookState() {
        try {
            guiState = GuiState.BOOKS;
            Object[][] data = daoFactory.getDaoInstance("Book").getAllAsArray();
            String[] columnNames = {"ID", "Title", "Author", "Genre", "Subgenre", "Pages", "Publisher", "Price"};
            redrawTable(data, columnNames);
            redrawComboBox(columnNames);
            searchTextField.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAllUser() {
        try {
            guiState = GuiState.USERS;
            Object[][] data = daoFactory.getDaoInstance("User").getAllAsArray();
            String[] columnNames = {"ID", "Username"};
            redrawTable(data, columnNames);
            redrawComboBox(columnNames);
            searchTextField.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAllHistory() {
        try {
            guiState = GuiState.HISTORY;
            Object[][] data = daoFactory.getDaoInstance("History").getAllAsArray();
            String[] columnNames = {"ID", "Buyer Username", "Book Title"};
            redrawTable(data, columnNames);
            redrawComboBox(columnNames);
            searchTextField.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void redrawTable(Object[][] data, String[] columnNames) {
        // update table
        mainTable.setModel(new DefaultTableModel(data, columnNames));
        mainScrollPane.setViewportView(mainTable);
        countLabel.setText("Count : " + data.length);
    }

    private void redrawComboBox(String[] items) {
        searchComboBox.removeAllItems();
        for (String item : items) {
            searchComboBox.addItem(item);
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
