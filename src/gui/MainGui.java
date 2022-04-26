package gui;

import dao.DaoFactory;
import entity.Book;
import entity.History;
import entity.User;
import entityutils.BookUtils;
import entityutils.HistoryUtils;
import entityutils.UserUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class MainGui extends JFrame {
    static String databaseUrl = "jdbc:sqlite:bookshop.db";
    public static DaoFactory daoFactory;
    private JTable mainTable;
    private JScrollPane mainScrollPane;
    private JLabel countLabel = new JLabel("Count : ");
    public static GuiState guiState = GuiState.BOOKS;
    private JComboBox<String> searchComboBox;
    private JTextField searchTextField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;

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
        // This listener make text field can enter to search
        searchTextField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
        // Set up search button
        searchComboBox = new JComboBox<>(BookUtils.readableColumnName);
        searchPanel.add(searchComboBox);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        searchButton.addActionListener(e -> search());
        topPanel.add(searchPanel);
        add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 1));
        JPanel bottomTextPanel = new JPanel();
        bottomTextPanel.setLayout(new FlowLayout());
        bottomTextPanel.add(countLabel);
        JPanel bottomButtonPanel = new JPanel();
        bottomButtonPanel.setLayout(new FlowLayout());
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refresh());
        bottomButtonPanel.add(refreshButton);
        addButton = new JButton("Add");
        addButton.addActionListener(e -> new AddWindow());
        bottomButtonPanel.add(addButton);
        updateButton = new JButton("Update");
        updateButton.addActionListener(e -> new UpdateWindow());
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> new DeleteWindow());
        bottomButtonPanel.add(addButton);
        bottomButtonPanel.add(updateButton);
        bottomButtonPanel.add(deleteButton);
        bottomPanel.add(bottomTextPanel);
        bottomPanel.add(bottomButtonPanel);
        add(bottomPanel, BorderLayout.SOUTH);

        // Initialize table with book table
        try {
            System.out.println("Initializing database...");
            Object[][] data = daoFactory.getBookDao().getAllAsArray();
            String[] columnNames = BookUtils.readableColumnName;
            countLabel.setText("Count : " + data.length);
            mainTable = new JTable(data, columnNames);
            mainTable.setDefaultEditor(Object.class, null);
            mainScrollPane = new JScrollPane(mainTable);
            System.out.println("Database initialized.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error on initialize database query\n" + e.getMessage() + "\nPlease check your database", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error on initialize database query : " + e.getMessage());
            System.exit(1);
        }

        add(mainScrollPane, BorderLayout.CENTER);

        pack();
        setSize(1000, 600);
        setVisible(true);
    }

    private void changeToBookState() {
        try {
            guiState = GuiState.BOOKS;
            redrawTable(daoFactory.getBookDao().getAllAsArray(), BookUtils.readableColumnName);
            redrawComboBox(BookUtils.readableColumnName);
            searchTextField.setText("");
            updateButton.setEnabled(true);
            deleteButton.setEnabled(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeToUserState() {
        try {
            guiState = GuiState.USERS;
            redrawTable(daoFactory.getUserDao().getAllAsArray(), UserUtils.readableColumnName);
            redrawComboBox(UserUtils.readableColumnName);
            searchTextField.setText("");
            updateButton.setEnabled(true);
            deleteButton.setEnabled(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeToHistoryState() {
        try {
            guiState = GuiState.HISTORY;
            redrawTable(daoFactory.getHistoryDao().getAllAsArray(), HistoryUtils.readableColumnName);
            redrawComboBox(HistoryUtils.readableColumnName);
            searchTextField.setText("");
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void search() {
        if (guiState == GuiState.BOOKS) {
            try {
                List<Book> data = daoFactory.getBookDao().queryForEq(BookUtils.queryColumnName[searchComboBox.getSelectedIndex()], searchTextField.getText());
                redrawTable(BookUtils.convertToArray(data), BookUtils.readableColumnName);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (guiState == GuiState.USERS) {
            try {
                List<User> data = daoFactory.getUserDao().queryForEq(UserUtils.queryColumnName[searchComboBox.getSelectedIndex()], searchTextField.getText());
                redrawTable(UserUtils.convertToArray(data), UserUtils.readableColumnName);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (guiState == GuiState.HISTORY) {
            try {
                List<History> data = daoFactory.getHistoryDao().queryForEq(HistoryUtils.queryColumnName[searchComboBox.getSelectedIndex()], searchTextField.getText());
                redrawTable(HistoryUtils.convertToArray(data), HistoryUtils.readableColumnName);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void refresh() {
        if (guiState == GuiState.BOOKS) {
            changeToBookState();
        } else if (guiState == GuiState.USERS) {
            changeToUserState();
        } else if (guiState == GuiState.HISTORY) {
            changeToHistoryState();
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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error on database connection\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error on database connection\n" + e.getMessage());
            System.exit(1);
        }
        new MainGui();
    }
}
