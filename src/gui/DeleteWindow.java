package gui;

import entity.Book;
import entity.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.sql.SQLException;

/**
 * Window for delete an entity. It will be created by the main window.
 *
 * Note that delete operation is not allowed for History.
 */
public class DeleteWindow extends JFrame {
    public DeleteWindow() {
        if (MainGui.guiState == GuiState.BOOKS) {
            setTitle("Delete Book");
        } else if (MainGui.guiState == GuiState.USERS) {
            setTitle("Delete User");
        } else if (MainGui.guiState == GuiState.HISTORY) {
            // This is normally not reached but just in case when the main UI is not lock the delete button on the history tab.
            JOptionPane.showMessageDialog(this, "Delete operation is not allowed for History.");
            dispose();
        }

        setLayout(new FlowLayout());

        JButton deleteButton = new JButton("Delete");

        JPanel mainTextFieldPanel = new JPanel();
        mainTextFieldPanel.setLayout(new GridLayout(GuiStateUtils.getColumnSize(MainGui.guiState), 2));
        if (MainGui.guiState == GuiState.BOOKS) {
            mainTextFieldPanel.add(new JLabel("Book ID:"));
            JTextField bookIdTextField = new JTextField(10);
            mainTextFieldPanel.add(bookIdTextField);
            mainTextFieldPanel.add(new JLabel("Title"));
            JTextField titleTextField = new JTextField(20);
            titleTextField.setEditable(false);
            mainTextFieldPanel.add(titleTextField);
            mainTextFieldPanel.add(new JLabel("Author"));
            JTextField authorTextField = new JTextField(20);
            authorTextField.setEditable(false);
            mainTextFieldPanel.add(authorTextField);
            mainTextFieldPanel.add(new JLabel("Genre"));
            JTextField genreTextField = new JTextField(20);
            genreTextField.setEditable(false);
            mainTextFieldPanel.add(genreTextField);
            mainTextFieldPanel.add(new JLabel("Subgenre"));
            JTextField subgenreTextField = new JTextField(20);
            subgenreTextField.setEditable(false);
            mainTextFieldPanel.add(subgenreTextField);
            mainTextFieldPanel.add(new JLabel("Pages"));
            JTextField heightTextField = new JTextField(20);
            heightTextField.setEditable(false);
            mainTextFieldPanel.add(heightTextField);
            mainTextFieldPanel.add(new JLabel("Publisher"));
            JTextField publisherTextField = new JTextField(20);
            publisherTextField.setEditable(false);
            mainTextFieldPanel.add(publisherTextField);
            mainTextFieldPanel.add(new JLabel("Price"));
            JTextField priceTextField = new JTextField(20);
            priceTextField.setEditable(false);
            mainTextFieldPanel.add(priceTextField);
            deleteButton.addActionListener(e -> {
                try {
                    MainGui.daoFactory.getBookDao().delete(new Book(Integer.parseInt(bookIdTextField.getText()), titleTextField.getText(), authorTextField.getText(), genreTextField.getText(), subgenreTextField.getText(), Integer.parseInt(heightTextField.getText()), publisherTextField.getText(), Double.parseDouble(priceTextField.getText())));
                    JOptionPane.showMessageDialog(this, "Book deleted successfully\nPlease click on the 'Refresh' button to see the changes");
                    dispose();
                } catch (NumberFormatException | SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error on deleting book from database\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            // Add a new listener that's bind with book ID to make it show up the info of the book in the text fields
            bookIdTextField.getDocument().addDocumentListener(new DocumentListener() {
                /**
                 * Gives notification that there was an insert into the document.  The
                 * range given by the DocumentEvent bounds the freshly inserted region.
                 *
                 * @param e the document event
                 */
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (bookIdTextField.getText().length() > 0) {
                        try {
                            Book newBook = MainGui.daoFactory.getBookDao().queryForId(Integer.parseInt(bookIdTextField.getText()));
                            if (newBook != null) {
                                titleTextField.setText(newBook.getTitle());
                                authorTextField.setText(newBook.getAuthor());
                                genreTextField.setText(newBook.getGenre());
                                subgenreTextField.setText(newBook.getSubgenre());
                                heightTextField.setText(String.valueOf(newBook.getHeight()));
                                publisherTextField.setText(newBook.getPublisher());
                                priceTextField.setText(String.valueOf(newBook.getPrice()));
                            } else {
                                titleTextField.setText("");
                                authorTextField.setText("");
                                genreTextField.setText("");
                                subgenreTextField.setText("");
                                heightTextField.setText("");
                                publisherTextField.setText("");
                                priceTextField.setText("");
                            }
                        } catch (NumberFormatException | SQLException ex) {
                            titleTextField.setText("");
                            authorTextField.setText("");
                            genreTextField.setText("");
                            subgenreTextField.setText("");
                            heightTextField.setText("");
                            publisherTextField.setText("");
                            priceTextField.setText("");
                        }
                    }
                }

                /**
                 * Gives notification that a portion of the document has been
                 * removed.  The range is given in terms of what the view last
                 * saw (that is, before updating sticky positions).
                 *
                 * @param e the document event
                 */
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (bookIdTextField.getText().length() > 0) {
                        try {
                            Book newBook = MainGui.daoFactory.getBookDao().queryForId(Integer.parseInt(bookIdTextField.getText()));
                            if (newBook != null) {
                                titleTextField.setText(newBook.getTitle());
                                authorTextField.setText(newBook.getAuthor());
                                genreTextField.setText(newBook.getGenre());
                                subgenreTextField.setText(newBook.getSubgenre());
                                heightTextField.setText(String.valueOf(newBook.getHeight()));
                                publisherTextField.setText(newBook.getPublisher());
                                priceTextField.setText(String.valueOf(newBook.getPrice()));
                            } else {
                                titleTextField.setText("");
                                authorTextField.setText("");
                                genreTextField.setText("");
                                subgenreTextField.setText("");
                                heightTextField.setText("");
                                publisherTextField.setText("");
                                priceTextField.setText("");
                            }
                        } catch (NumberFormatException | SQLException ex) {
                            titleTextField.setText("");
                            authorTextField.setText("");
                            genreTextField.setText("");
                            subgenreTextField.setText("");
                            heightTextField.setText("");
                            publisherTextField.setText("");
                            priceTextField.setText("");
                        }
                    }
                }

                /**
                 * Gives notification that an attribute or set of attributes changed.
                 *
                 * @param e the document event
                 */
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (bookIdTextField.getText().length() > 0) {
                        try {
                            Book newBook = MainGui.daoFactory.getBookDao().queryForId(Integer.parseInt(bookIdTextField.getText()));
                            if (newBook != null) {
                                titleTextField.setText(newBook.getTitle());
                                authorTextField.setText(newBook.getAuthor());
                                genreTextField.setText(newBook.getGenre());
                                subgenreTextField.setText(newBook.getSubgenre());
                                heightTextField.setText(String.valueOf(newBook.getHeight()));
                                publisherTextField.setText(newBook.getPublisher());
                                priceTextField.setText(String.valueOf(newBook.getPrice()));
                            } else {
                                titleTextField.setText("");
                                authorTextField.setText("");
                                genreTextField.setText("");
                                subgenreTextField.setText("");
                                heightTextField.setText("");
                                publisherTextField.setText("");
                                priceTextField.setText("");
                            }
                        } catch (NumberFormatException | SQLException ex) {
                            titleTextField.setText("");
                            authorTextField.setText("");
                            genreTextField.setText("");
                            subgenreTextField.setText("");
                            heightTextField.setText("");
                            publisherTextField.setText("");
                            priceTextField.setText("");
                        }
                    }
                }
            });
        } else if (MainGui.guiState == GuiState.USERS) {
            mainTextFieldPanel.add(new JLabel("User ID"));
            JTextField userIdTextField = new JTextField(20);
            mainTextFieldPanel.add(userIdTextField);
            mainTextFieldPanel.add(new JLabel("Username"));
            JTextField usernameTextField = new JTextField(20);
            usernameTextField.setEditable(false);
            mainTextFieldPanel.add(usernameTextField);
            deleteButton.addActionListener(e -> {
                try {
                    MainGui.daoFactory.getUserDao().delete(new User(Integer.parseInt(userIdTextField.getText()),usernameTextField.getText()));
                    JOptionPane.showMessageDialog(this, "User deleted successfully\nPlease click on the 'Refresh' button to see the changes");
                    dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error on deleting user from database\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            // Add a new listener to the user ID text field to make it update the username text field of the user.
            userIdTextField.getDocument().addDocumentListener(new DocumentListener() {
                /**
                 * Gives notification that there was an insert into the document.  The
                 * range given by the DocumentEvent bounds the freshly inserted region.
                 *
                 * @param e the document event
                 */
                @Override
                public void insertUpdate(DocumentEvent e) {
                    try {
                        User newUser = MainGui.daoFactory.getUserDao().queryForId(Integer.parseInt(userIdTextField.getText()));
                        if (newUser != null) {
                            usernameTextField.setText(newUser.getUsername());
                        } else {
                            usernameTextField.setText("");
                        }
                    } catch (SQLException ex) {
                        usernameTextField.setText("");
                    }
                }

                /**
                 * Gives notification that a portion of the document has been
                 * removed.  The range is given in terms of what the view last
                 * saw (that is, before updating sticky positions).
                 *
                 * @param e the document event
                 */
                @Override
                public void removeUpdate(DocumentEvent e) {
                    try {
                        User newUser = MainGui.daoFactory.getUserDao().queryForId(Integer.parseInt(userIdTextField.getText()));
                        if (newUser != null) {
                            usernameTextField.setText(newUser.getUsername());
                        } else {
                            usernameTextField.setText("");
                        }
                    } catch (SQLException ex) {
                        usernameTextField.setText("");
                    }
                }

                /**
                 * Gives notification that an attribute or set of attributes changed.
                 *
                 * @param e the document event
                 */
                @Override
                public void changedUpdate(DocumentEvent e) {
                    try {
                        User newUser = MainGui.daoFactory.getUserDao().queryForId(Integer.parseInt(userIdTextField.getText()));
                        if (newUser != null) {
                            usernameTextField.setText(newUser.getUsername());
                        } else {
                            usernameTextField.setText("");
                        }
                    } catch (SQLException ex) {
                        usernameTextField.setText("");
                    }
                }
            });
        }

        add(mainTextFieldPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
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
