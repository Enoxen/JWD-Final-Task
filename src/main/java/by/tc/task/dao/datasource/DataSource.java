package by.tc.task.dao.datasource;

import by.tc.task.dao.exception.ConnectionPoolException;
import by.tc.task.dao.exception.DataSourceDAOException;

import java.sql.Connection;

/**
 * Created by Y50-70 on 09.01.2018.
 */
public class DataSource {
    private static ConnectionPool connectionPool = new ConnectionPool();

    public static void init() throws DataSourceDAOException {
        try {
            connectionPool.initPool();
        } catch (ConnectionPoolException ex) {
            throw new DataSourceDAOException(ex);
        }
    }

    public static Connection getConnection() throws DataSourceDAOException {
        try {
            return connectionPool.getConnectionFromPool();
        } catch (ConnectionPoolException ex) {
            throw new DataSourceDAOException(ex);
        }
    }

    public static void closeConnection(Connection connection) {
        connectionPool.returnConnectionToPool(connection);
    }

    public static void destroy() throws DataSourceDAOException{
        try {
            connectionPool.closePool();
        } catch (ConnectionPoolException ex) {
            throw new DataSourceDAOException(ex);
        }
    }

    private DataSource() {
    }
}
