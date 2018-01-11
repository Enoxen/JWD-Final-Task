package by.tc.task.dao.exception;

import by.tc.task.exception.DAOException;

/**
 * Created by Y50-70 on 11.01.2018.
 */
public class ChangeUserDataDAOException extends DAOException {
    public ChangeUserDataDAOException() {
    }

    public ChangeUserDataDAOException(String message) {
        super(message);
    }

    public ChangeUserDataDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChangeUserDataDAOException(Throwable cause) {
        super(cause);
    }
}
