package gui;

import entity.Book;
import entity.History;
import entity.User;

public class GuiStateUtils {
    public static int getColumnSize(GuiState state) {
        return switch (state) {
            case BOOKS -> Book.queryColumnName.length;
            case USERS -> User.queryColumnName.length;
            case HISTORY -> History.queryColumnName.length;
        };
    }
}
