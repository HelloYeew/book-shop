package entity.utils;

import dao.DaoFactory;
import entity.History;

import java.sql.SQLException;
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
    public static String[] readableColumnName = {"ID", "Buyer", "Book"};

    /**
     * Real column of the history table that can be used in database.
     */
    public static String[] queryColumnName = {"id", "buyer_id", "book_id"};

    /**
     * Convert a list of History that may be obtained from database query to an Object[][] that can be used in table UI.
     * @param histories List of History that may be obtained from database query.
     * @return Object[][] that can be used in table UI.
     */
    public static Object[][] convertToArray(List<History> histories, DaoFactory daoFactory) {
        Object[][] data = new Object[histories.size()][];
        for (int i = 0; i < histories.size(); i++) {
            History history = histories.get(i);
            try {
                data[i] = new Object[]{history.getId(), daoFactory.getUserDao().queryForId(history.getBuyer().getId()), daoFactory.getBookDao().queryForId(history.getBook().getId())};
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return data;
    }
}
