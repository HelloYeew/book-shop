package dao;

import java.sql.SQLException;

public interface DaoInstance {
    /**
     * Return 2D array that can be render to table in GUI.
     * @return 2D array that can be render to table in GUI.
     * @throws SQLException if SQL query failed.
     */
    Object[][] getAllAsArray() throws SQLException;
}
