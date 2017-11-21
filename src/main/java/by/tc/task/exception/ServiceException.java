package by.tc.task.exception;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public class ServiceException extends Exception{
    public ServiceException(){}
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(String message, Throwable exception){
        super(message,exception);
    }
    public ServiceException(Throwable exception){
        super(exception);
    }
}
