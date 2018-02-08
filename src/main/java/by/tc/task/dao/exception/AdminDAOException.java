package by.tc.task.dao.exception;

/**
 * Created by Y50-70 on 12.01.2018.
 */
public class AdminDAOException extends DAOException {
    public AdminDAOException() {
    }

    public AdminDAOException(String message) {
        super(message);
    }

    public AdminDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminDAOException(Throwable cause) {
        super(cause);
    }
}
