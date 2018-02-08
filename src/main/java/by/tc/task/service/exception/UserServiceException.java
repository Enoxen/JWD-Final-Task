package by.tc.task.service.exception;

/**
 * Created by Y50-70 on 05.02.2018.
 */
public class UserServiceException extends ServiceException {
    public UserServiceException() {
    }

    public UserServiceException(String message) {
        super(message);
    }

    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserServiceException(Throwable cause) {
        super(cause);
    }
}
