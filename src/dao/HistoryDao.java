package dao;

import entity.History;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class HistoryDao extends BaseDaoImpl<History, Integer> implements DaoInstance {
    public HistoryDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, History.class);
    }

    /**
     * Return 2D array that can be render to table in GUI.
     *
     * @return 2D array that can be render to table in GUI.
     * @throws SQLException if SQL query failed.
     */
    public Object[][] getAllAsArray() throws SQLException {
        return History.convertToArray(queryForAll());
    }
}
