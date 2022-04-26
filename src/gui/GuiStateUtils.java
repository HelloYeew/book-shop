package gui;

import entityutils.BookUtils;
import entityutils.HistoryUtils;
import entityutils.UserUtils;

public class GuiStateUtils {
    public static int getColumnSize(GuiState state) {
        return switch (state) {
            case BOOKS -> BookUtils.queryColumnName.length;
            case USERS -> UserUtils.queryColumnName.length;
            case HISTORY -> HistoryUtils.queryColumnName.length;
        };
    }
}
