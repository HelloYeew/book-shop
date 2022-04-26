package dao;

import entity.History;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * DAO for History entity by extending BaseDaoImpl from ORMLite.
 *
 * See {@link History} for more information on this entity.
 */
public class HistoryDao extends BaseDaoImpl<History, Integer> {
    /**
     * Constructor that takes connection source and creates DAO.
     * @param connectionSource Database connection source.
     * @throws SQLException if failed to connect to database.
     */
    public HistoryDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, History.class);
    }
}
