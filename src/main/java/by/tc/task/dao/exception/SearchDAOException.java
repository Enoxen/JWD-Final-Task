package by.tc.task.dao.exception;

/**
 * Created by Y50-70 on 31.01.2018.
 */
public class SearchDAOException extends DAOException {
    public SearchDAOException() {
    }

    public SearchDAOException(String message) {
        super(message);
    }

    public SearchDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchDAOException(Throwable cause) {
        super(cause);
    }
}
