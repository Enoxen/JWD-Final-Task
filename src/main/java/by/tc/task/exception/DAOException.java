package by.tc.task.exception;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public class DAOException extends Exception {
    public DAOException(){}
    public DAOException(String message){
        super(message);
    }
    public DAOException(String message, Throwable exception){
        super(message,exception);
    }
    public DAOException(Throwable exception){
        super(exception);
    }
}
