package by.tc.task.service.exception;

/**
 * Created by Y50-70 on 05.02.2018.
 */
public class AuthServiceException extends ServiceException {
    public AuthServiceException() {
    }

    public AuthServiceException(String message) {
        super(message);
    }

    public AuthServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthServiceException(Throwable cause) {
        super(cause);
    }
}
