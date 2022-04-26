package gui;

import entity.utils.BookUtils;
import entity.utils.HistoryUtils;
import entity.utils.UserUtils;

/**
 * Static utility class for utility methods related to the {@link GuiState} enum that will use in UI.
 */
public class GuiStateUtils {
    /**
     * Get the table column size of the current table by passing the {@link GuiState}.
     * @param state The current {@link GuiState}.
     * @return The table column size of the current table.
     */
    public static int getColumnSize(GuiState state) {
        return switch (state) {
            case BOOKS -> BookUtils.queryColumnName.length;
            case USERS -> UserUtils.queryColumnName.length;
            case HISTORY -> HistoryUtils.queryColumnName.length;
        };
    }
}
