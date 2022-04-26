package entityutils;

import entity.History;

import java.util.List;

public class HistoryUtils {
    public static String[] readableColumnName = {"ID", "Buyer ID", "Book ID"};

    public static String[] queryColumnName = {"id", "buyer_id", "book_id"};

    public static Object[][] convertToArray(List<History> histories) {
        Object[][] data = new Object[histories.size()][];
        for (int i = 0; i < histories.size(); i++) {
            History history = histories.get(i);
            data[i] = new Object[]{history.getId(), history.getBuyer().getId(), history.getBook().getId()};
        }
        return data;
    }

    public static Object[][] convertToArray(History history) {
        Object[][] data = new Object[1][];
        data[0] = new Object[]{history.getId(), history.getBuyer().getId(), history.getBook().getId()};
        return data;
    }
}
