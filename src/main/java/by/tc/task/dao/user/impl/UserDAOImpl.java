package by.tc.task.dao.user.impl;

import by.tc.task.dao.auth.help.AuthHelp;
import by.tc.task.dao.constant.DAODbQuery;
import by.tc.task.dao.datasource.DataSource;
import by.tc.task.dao.exception.AuthDAOException;
import by.tc.task.dao.exception.ChangeUserDataDAOException;
import by.tc.task.dao.user.UserDAO;
import by.tc.task.dao.util.Encryptor;
import by.tc.task.exception.DataSourceDAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.crypto.Data;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Created by Y50-70 on 12.11.2017.
 */
public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);


    @Override
    public boolean changePassword(String login, String oldPassword, String newPassword) throws ChangeUserDataDAOException {
        Connection connection = null;
        PreparedStatement updatePassword = null;
        try{
            connection = DataSource.getConnection();

            if(AuthHelp.authPassword(login,oldPassword,connection)) {
                updatePassword = connection.prepareStatement(DAODbQuery.SQL_UPDATE_USER_PASSWORD_BY_LOGIN);

                String salt = Encryptor.generateSalt();
                String hashedPassword = Encryptor.getPasswordHashCode(newPassword, salt);

                updatePassword.setString(1, hashedPassword);
                updatePassword.setString(2, salt);
                updatePassword.setString(3,login);

                int rowsUpdate = updatePassword.executeUpdate();
                return rowsUpdate != 0;
            }
            else return false;
        } catch (DataSourceDAOException|SQLException|NoSuchAlgorithmException e) {
            throw new ChangeUserDataDAOException("error while changing password",e);
        }
        finally {
            DataSource.closeConnection(connection);

            if(updatePassword != null){
                try {
                    updatePassword.close();
                } catch (SQLException e) {
                    logger.error(e.getStackTrace());
                }
            }
        }
    }

    @Override
    public boolean changePassword(String email, String newPassword) throws ChangeUserDataDAOException {
        Connection connection = null;
        PreparedStatement updatePassword = null;
        try{
            connection = DataSource.getConnection();

            if(AuthHelp.authEmail(email,connection)){
                updatePassword = connection.prepareStatement(DAODbQuery.SQL_UPDATE_USER_PASSWORD_BY_EMAIL);
                updatePassword.setString(1,email);
                updatePassword.setString(2,newPassword);
                int rowsUpdate = updatePassword.executeUpdate();
                return rowsUpdate != 0;
            }
            else{
                return false;
            }
        } catch (ChangeUserDataDAOException|DataSourceDAOException|SQLException e) {
           throw new ChangeUserDataDAOException("error when changing password by email",e);
        }
        finally {
            DataSource.closeConnection(connection);
            if(updatePassword != null){
                try {
                    updatePassword.close();
                } catch (SQLException e) {
                    logger.error(e.getStackTrace());
                }
            }
        }
    }

    @Override
    public boolean changeLogin(String oldLogin, String newLogin, String confirmPassword) throws AuthDAOException {
        Connection connection = null;
        PreparedStatement updateLogin = null;
        try{
            connection = DataSource.getConnection();
            if(AuthHelp.authPassword(oldLogin, confirmPassword, connection)){

                updateLogin = connection.prepareStatement(DAODbQuery.SQL_UPDATE_USER_LOGIN);

                updateLogin.setString(1,newLogin);
                int userId = AuthHelp.getIdByLogin(oldLogin,connection);
                updateLogin.setInt(2,userId);

                int rowsUpdate = updateLogin.executeUpdate();
                return rowsUpdate != 0;
            }
            else {
                return false;
            }
        } catch (DataSourceDAOException|SQLException|NoSuchAlgorithmException e) {
            throw new AuthDAOException("change login error", e);
        }
    }

    @Override
    public boolean changeEmail(String login, String newEmail, String password) throws AuthDAOException {
        Connection connection = null;
        PreparedStatement updateEmail = null;
        try{
            connection = DataSource.getConnection();
            if(AuthHelp.authPassword(login,password,connection)){
                updateEmail = connection.prepareStatement(DAODbQuery.SQL_UPDATE_USER_EMAIL_BY_LOGIN);
                updateEmail.setString(1,newEmail);
                updateEmail.setString(2,login);
                int rowsUpdate = updateEmail.executeUpdate();
                return rowsUpdate != 0;
            }
            else{
                return false;
            }
        } catch (SQLException|NoSuchAlgorithmException|DataSourceDAOException e) {
            throw new AuthDAOException("change email error", e);
        }
    }
}