package by.tc.task.service.exception;

/**
 * Created by Y50-70 on 07.02.2018.
 */
public class AdminServiceException extends ServiceException {
    public AdminServiceException() {
    }

    public AdminServiceException(String message) {
        super(message);
    }

    public AdminServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminServiceException(Throwable cause) {
        super(cause);
    }
}
