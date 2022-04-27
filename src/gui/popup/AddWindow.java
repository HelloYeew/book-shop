package gui.popup;

import entity.Book;
import entity.History;
import entity.User;
import gui.GuiState;
import gui.GuiStateUtils;
import gui.MainGui;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Window for adding a new entity. It will be created by the main window.
 */
public class AddWindow extends JFrame {
    /**
     * Constructor for the AddWindow.
     */
    public AddWindow(MainGui mainGui) {
        if (MainGui.guiState == GuiState.BOOKS) {
            setTitle("Add Book");
        } else if (MainGui.guiState == GuiState.USERS) {
            setTitle("Add User");
        } else if (MainGui.guiState == GuiState.HISTORY) {
            setTitle("Add History");
        }

        setLayout(new FlowLayout());

        JButton addButton = new JButton("Add");

        JPanel mainTextFieldPanel = new JPanel();
        mainTextFieldPanel.setLayout(new GridLayout(GuiStateUtils.getColumnSize(MainGui.guiState)-1, 2));
        if (MainGui.guiState == GuiState.BOOKS) {
            mainTextFieldPanel.add(new JLabel("Title"));
            JTextField titleTextField = new JTextField(20);
            mainTextFieldPanel.add(titleTextField);
            mainTextFieldPanel.add(new JLabel("Author"));
            JTextField authorTextField = new JTextField(20);
            mainTextFieldPanel.add(authorTextField);
            mainTextFieldPanel.add(new JLabel("Genre"));
            JTextField genreTextField = new JTextField(20);
            mainTextFieldPanel.add(genreTextField);
            mainTextFieldPanel.add(new JLabel("Subgenre"));
            JTextField subgenreTextField = new JTextField(20);
            mainTextFieldPanel.add(subgenreTextField);
            mainTextFieldPanel.add(new JLabel("Pages"));
            JTextField heightTextField = new JTextField(20);
            mainTextFieldPanel.add(heightTextField);
            mainTextFieldPanel.add(new JLabel("Publisher"));
            JTextField publisherTextField = new JTextField(20);
            mainTextFieldPanel.add(publisherTextField);
            mainTextFieldPanel.add(new JLabel("Price"));
            JTextField priceTextField = new JTextField(20);
            mainTextFieldPanel.add(priceTextField);
            addButton.addActionListener(e -> {
                try {
                    MainGui.daoFactory.getBookDao().create(new Book(titleTextField.getText(), authorTextField.getText(), genreTextField.getText(), subgenreTextField.getText(), Integer.parseInt(heightTextField.getText()), publisherTextField.getText(), Double.parseDouble(priceTextField.getText())));
                    JOptionPane.showMessageDialog(this, "Book added successfully!");
                    mainGui.refresh();
                    dispose();
                } catch (NumberFormatException | SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error on adding book to database\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        } else if (MainGui.guiState == GuiState.USERS) {
            mainTextFieldPanel.add(new JLabel("Username"));
            JTextField usernameTextField = new JTextField(20);
            mainTextFieldPanel.add(usernameTextField);
            addButton.addActionListener(e -> {
                try {
                    if (!usernameTextField.getText().equals("")) {
                        MainGui.daoFactory.getUserDao().create(new User(usernameTextField.getText()));
                        JOptionPane.showMessageDialog(this, "User added successfully!");
                        mainGui.refresh();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Username cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error on adding user to database\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        } else if (MainGui.guiState == GuiState.HISTORY) {
            mainTextFieldPanel.add(new JLabel("User ID"));
            JTextField userIdTextField = new JTextField(20);
            mainTextFieldPanel.add(userIdTextField);
            mainTextFieldPanel.add(new JLabel("Book ID"));
            JTextField bookIdTextField = new JTextField(20);
            mainTextFieldPanel.add(bookIdTextField);
            addButton.addActionListener(e -> {
                try {
                    MainGui.daoFactory.getHistoryDao().create(new History(MainGui.daoFactory.getUserDao().queryForId(Integer.parseInt(userIdTextField.getText())), MainGui.daoFactory.getBookDao().queryForId(Integer.parseInt(bookIdTextField.getText()))));
                    JOptionPane.showMessageDialog(this, "History added successfully!");
                    mainGui.refresh();
                    dispose();
                } catch (NumberFormatException | SQLException | NullPointerException ex) {
                    JOptionPane.showMessageDialog(this, "Error on adding history to database\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }

        add(mainTextFieldPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        JButton closeButton = new JButton("Cancel");
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
