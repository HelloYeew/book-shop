package gui;

import dao.DaoFactory;
import entity.Book;
import entity.History;
import entity.User;
import entity.utils.BookUtils;
import entity.utils.HistoryUtils;
import entity.utils.UserUtils;
import gui.popup.AddWindow;
import gui.popup.DeleteWindow;
import gui.popup.UpdateWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

/**
 * Main GUI class
 */
public class MainGui extends JFrame {
    /**
     * Link of the database connection URL.
     */
    static String databaseUrl = "jdbc:sqlite:bookshop.db";

    /**
     * {@link DaoFactory} instance that will use to create DAO objects and maintain the database connection.
     */
    public static DaoFactory daoFactory;

    /**
     * Main table that's inside center {@link JScrollPane} at the center of the window.
     */
    private JTable mainTable;

    /**
     * {@link JScrollPane} that contains the main table.
     */
    private JScrollPane mainScrollPane;

    /**
     * Count label that shows the number of rows in the main table.
     */
    private JLabel countLabel = new JLabel("Count : ");

    /**
     * The current state of the UI. Mainly state the current table that which is shown in the main table.
     */
    public static GuiState guiState = GuiState.BOOKS;

    /**
     * {@link JComboBox} that contains the list of the search query. Mainly updated with the columns of the current table.
     */
    private JComboBox<String> searchComboBox;

    /**
     * {@link JTextField} that contains the search query.
     */
    private JTextField searchTextField;

    /**
     * Add button that will create a new instance of {@link AddWindow}
     */
    private JButton addButton;

    /**
     * Update button that will create a new instance of {@link UpdateWindow}
     */
    private JButton updateButton;

    /**
     * Delete button that will create a new instance of {@link DeleteWindow}
     */
    private JButton deleteButton;

    public MainGui() {
        super("Book shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);

        // Create the top panel of the window include the button to change the state of the UI and the search panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));

        // Button panel to change the state of the UI
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
        // Search text field
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchTextField = new JTextField(20);
        // This listener make text field can press enter to search
        searchTextField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
        // Set up search button and combo box
        searchComboBox = new JComboBox<>(BookUtils.readableColumnName);
        searchPanel.add(searchComboBox);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        searchButton.addActionListener(e -> search());
        topPanel.add(searchPanel);
        // Add the top panel to the window
        add(topPanel, BorderLayout.NORTH);

        // Bottom panel to show the count of the rows, refresh button and CRUD operation buttons
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
        addButton.addActionListener(e -> new AddWindow(this));
        bottomButtonPanel.add(addButton);
        updateButton = new JButton("Update");
        updateButton.addActionListener(e -> new UpdateWindow(this));
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> new DeleteWindow(this));
        bottomButtonPanel.add(addButton);
        bottomButtonPanel.add(updateButton);
        bottomButtonPanel.add(deleteButton);
        bottomPanel.add(bottomTextPanel);
        bottomPanel.add(bottomButtonPanel);
        add(bottomPanel, BorderLayout.SOUTH);

        // Initialize the main table with book table
        try {
            System.out.println("Initializing database...");
            Object[][] data = BookUtils.convertToArray(daoFactory.getBookDao().queryForAll());
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

    /**
     * Change the main table to show the book table and change the state of the UI to focus on the book table.
     */
    private void changeToBookState() {
        try {
            guiState = GuiState.BOOKS;
            redrawTable(BookUtils.convertToArray(daoFactory.getBookDao().queryForAll()), BookUtils.readableColumnName);
            redrawComboBox(BookUtils.readableColumnName);
            searchTextField.setText("");
            updateButton.setEnabled(true);
            deleteButton.setEnabled(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Change the main table to show the user table and change the state of the UI to focus on the user table.
     */
    private void changeToUserState() {
        try {
            guiState = GuiState.USERS;
            redrawTable(UserUtils.convertToArray(daoFactory.getUserDao().queryForAll()), UserUtils.readableColumnName);
            redrawComboBox(UserUtils.readableColumnName);
            searchTextField.setText("");
            updateButton.setEnabled(true);
            deleteButton.setEnabled(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Change the main table to show the history table and change the state of the UI to focus on the history table.
     */
    private void changeToHistoryState() {
        try {
            guiState = GuiState.HISTORY;
            redrawTable(HistoryUtils.convertToArray(daoFactory.getHistoryDao().queryForAll(), daoFactory), HistoryUtils.readableColumnName);
            redrawComboBox(HistoryUtils.readableColumnName);
            searchTextField.setText("");
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
        } catch (SQLException | RuntimeException e) {
            JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Update the table with the new data that's come from search result.
     */
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
                redrawTable(HistoryUtils.convertToArray(data, daoFactory), HistoryUtils.readableColumnName);
            } catch (SQLException | RuntimeException e) {
                JOptionPane.showMessageDialog(this, "Error on database query\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Refresh the table
     */
    public void refresh() {
        if (guiState == GuiState.BOOKS) {
            changeToBookState();
        } else if (guiState == GuiState.USERS) {
            changeToUserState();
        } else if (guiState == GuiState.HISTORY) {
            changeToHistoryState();
        }
    }

    /**
     * Get the Object[][] data and the String[] columnName from the converted database query result and redraw the table and change the count label.
     *
     * @param data Converted database query result
     * @param columnNames Current list of column names of the current focus entity. Can be obtained via the entity's utils class.
     */
    private void redrawTable(Object[][] data, String[] columnNames) {
        mainTable.setModel(new DefaultTableModel(data, columnNames));
        mainScrollPane.setViewportView(mainTable);
        countLabel.setText("Count : " + data.length);
    }

    /**
     * Update the combo box with the new state of the GUI.
     * @param items List of column name in the current focus entity. Can be obtained via the entity's utils class.
     */
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
