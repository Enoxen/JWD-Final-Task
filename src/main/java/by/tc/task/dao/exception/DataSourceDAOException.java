package by.tc.task.dao.exception;

/**
 * Created by Y50-70 on 09.01.2018.
 */
public class DataSourceDAOException extends DAOException{
    public DataSourceDAOException() {
    }

    public DataSourceDAOException(String message) {
        super(message);
    }

    public DataSourceDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataSourceDAOException(Throwable cause) {
        super(cause);
    }
}

