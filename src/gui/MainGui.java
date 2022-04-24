package gui;

import dao.DaoFactory;
import entity.Book;
import entity.History;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;

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
        setFocusable(true);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));

        JPanel buttonTopPanel = new JPanel();
        buttonTopPanel.setLayout(new FlowLayout());
        JButton showAllBooksButton = new JButton("Books");
        showAllBooksButton.addActionListener(e -> changeToBookState());
        buttonTopPanel.add(showAllBooksButton);
        JButton showAllUserButton = new JButton("Users");
        showAllUserButton.addActionListener(e -> changeToUserState());
        buttonTopPanel.add(showAllUserButton);
        JButton showAllHistoryButton = new JButton("History");
        showAllHistoryButton.addActionListener(e -> changeToHistoryState());
        buttonTopPanel.add(showAllHistoryButton);
        topPanel.add(buttonTopPanel);
        // Add search text field below
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchTextField = new JTextField(20);
        searchTextField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
        // Set up search button
        String[] bookSearchFields = {"ID", "Title", "Author", "Genre", "Subgenre", "Pages", "Publisher", "Price"};
        searchComboBox = new JComboBox<>(bookSearchFields);
        searchPanel.add(searchComboBox);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        searchButton.addActionListener(e -> search());
        topPanel.add(searchPanel);
        add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        JLabel countLabel = new JLabel("Count : ");
        bottomPanel.add(countLabel);
        add(bottomPanel, BorderLayout.SOUTH);

        try {
            Object[][] data = daoFactory.getBookDao().getAllAsArray();
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
        setSize(1000, 600);
        setVisible(true);
    }

    private void changeToBookState() {
        try {
            redrawTable(daoFactory.getBookDao().getAllAsArray(), Book.readableColumnName);
            redrawComboBox(Book.readableColumnName);
            searchTextField.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeToUserState() {
        try {
            guiState = GuiState.USERS;
            redrawTable(daoFactory.getUserDao().getAllAsArray(), User.readableColumnName);
            redrawComboBox(User.readableColumnName);
            searchTextField.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeToHistoryState() {
        try {
            guiState = GuiState.HISTORY;
            redrawTable(daoFactory.getHistoryDao().getAllAsArray(), History.columnName);
            redrawComboBox(History.columnName);
            searchTextField.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void search() {
        // TODO: Fix improper state change
        System.out.println("Current state : " + guiState);
        // If the search text is empty, show all the data instead
        if (searchTextField.getText().isEmpty()) {
            if (guiState == GuiState.BOOKS) {
                changeToBookState();
            } else if (guiState == GuiState.USERS) {
                changeToUserState();
            } else if (guiState == GuiState.HISTORY) {
                changeToHistoryState();
            }
        } else {
            if (guiState == GuiState.BOOKS) {
                try {
                    List<Book> data = daoFactory.getBookDao().queryForEq(Book.readableColumnName[searchComboBox.getSelectedIndex()], searchTextField.getText());
                    redrawTable(Book.convertToArray(data), Book.readableColumnName);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (guiState == GuiState.USERS) {
                try {
                    List<User> data = daoFactory.getUserDao().queryForEq(User.readableColumnName[searchComboBox.getSelectedIndex()], searchTextField.getText());
                    redrawTable(User.convertToArray(data), User.readableColumnName);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (guiState == GuiState.HISTORY) {
                try {
                    List<History> data = daoFactory.getHistoryDao().queryForEq(History.columnName[searchComboBox.getSelectedIndex()], searchTextField.getText());
                    redrawTable(History.convertToArray(data), History.columnName);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void redrawTable(Object[][] data, String[] columnNames) {
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
