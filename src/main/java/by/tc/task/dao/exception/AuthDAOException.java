package by.tc.task.dao.exception;

/**
 * Created by Y50-70 on 11.01.2018.
 */
public class AuthDAOException extends DAOException {
    public AuthDAOException() {
    }

    public AuthDAOException(String message) {
        super(message);
    }

    public AuthDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthDAOException(Throwable cause) {
        super(cause);
    }
}
