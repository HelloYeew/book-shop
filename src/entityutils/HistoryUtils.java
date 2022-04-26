package entityutils;

import entity.History;

import java.util.List;

/**
 * Utility class for History entity.
 *
 * See {@link History} for more information on the entity.
 */
public class HistoryUtils {
    /**
     * Column name of the history table that can be used in UI.
     */
    public static String[] readableColumnName = {"ID", "Buyer ID", "Book ID"};

    /**
     * Real column of the history table that can be used in database.
     */
    public static String[] queryColumnName = {"id", "buyer_id", "book_id"};

    /**
     * Convert a list of History that may be obtained from database query to an Object[][] that can be used in table UI.
     * @param histories List of History that may be obtained from database query.
     * @return Object[][] that can be used in table UI.
     */
    public static Object[][] convertToArray(List<History> histories) {
        Object[][] data = new Object[histories.size()][];
        for (int i = 0; i < histories.size(); i++) {
            History history = histories.get(i);
            data[i] = new Object[]{history.getId(), history.getBuyer().getId(), history.getBook().getId()};
        }
        return data;
    }

    /**
     * Convert one book object to an Object[][] that can be used in table UI in case of one history.
     * @param history History object that may be gotten only one history from database query.
     * @return Object[][] that can be used in table UI.
     */
    public static Object[][] convertToArray(History history) {
        Object[][] data = new Object[1][];
        data[0] = new Object[]{history.getId(), history.getBuyer().getId(), history.getBook().getId()};
        return data;
    }
}
