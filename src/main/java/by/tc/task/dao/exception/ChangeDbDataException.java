package by.tc.task.dao.exception;

/**
 * Created by Y50-70 on 29.01.2018.
 */
public class ChangeDbDataException extends DAOException {
    public ChangeDbDataException() {
    }

    public ChangeDbDataException(String message) {
        super(message);
    }

    public ChangeDbDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChangeDbDataException(Throwable cause) {
        super(cause);
    }
}
