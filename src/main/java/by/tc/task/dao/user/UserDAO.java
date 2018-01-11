package by.tc.task.dao.user;


import by.tc.task.dao.exception.AuthDAOException;
import by.tc.task.dao.exception.ChangeUserDataDAOException;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public interface UserDAO {
    boolean changePassword(String login,String oldPassword,String newPassword) throws ChangeUserDataDAOException;
    boolean changePassword(String email,String newPassword) throws ChangeUserDataDAOException;
    boolean changeLogin(String oldLogin, String newLogin, String confirmPassword) throws AuthDAOException;
}
