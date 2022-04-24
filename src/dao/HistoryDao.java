package dao;

import entity.History;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class HistoryDao extends BaseDaoImpl<History, Integer> implements DaoInstance {
    public HistoryDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, History.class);
    }

    public List<History> getAll() throws SQLException {
        return this.queryForAll();
    }

    public Object[][] convertToArray(List<History> histories) {
        Object[][] data = new Object[histories.size()][];
        for (int i = 0; i < histories.size(); i++) {
            History history = histories.get(i);
            data[i] = new Object[]{history.getId(), history.getBuyer().getUsername(), history.getBook().getTitle()};
        }
        return data;
    }

    /**
     * Return 2D array that can be render to table in GUI.
     *
     * @return 2D array that can be render to table in GUI.
     * @throws SQLException if SQL query failed.
     */
    @Override
    public Object[][] getAllAsArray() throws SQLException {
        return convertToArray(getAll());
    }
}
