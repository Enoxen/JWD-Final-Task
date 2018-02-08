package by.tc.task.dao.exception;

/**
 * Created by Y50-70 on 09.01.2018.
 */
public class ConnectionPoolException extends DAOException {
    public ConnectionPoolException() {
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
