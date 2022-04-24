package dao;

import entity.Book;
import entity.History;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class HistoryDao extends BaseDaoImpl<History, Integer> implements DaoInstance {
    public HistoryDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, History.class);
    }

    @Override
    public Object[][] getAllAsArray() throws SQLException {
        List<History> histories = this.queryForAll();
        Object[][] data = new Object[histories.size()][];
        for (int i = 0; i < histories.size(); i++) {
            History history = histories.get(i);
            data[i] = new Object[]{history.getId(), history.getBuyer().getUsername(), history.getBook().getTitle()};
        }
        return data;
    }
}
