package by.tc.task.dao.auth.help;

import by.tc.task.dao.constant.DAODbQuery;
import by.tc.task.dao.exception.ChangeUserDataDAOException;
import by.tc.task.dao.util.Encryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Y50-70 on 12.01.2018.
 */
public class AuthHelp {
    private static final Logger logger = LogManager.getLogger(AuthHelp.class);

    public static boolean authEmail(String email, Connection connection) throws ChangeUserDataDAOException, SQLException {
        PreparedStatement emailAuth = null;
        ResultSet dbResponse = null;
        try{
            emailAuth = connection.prepareStatement(DAODbQuery.SQL_AUTH_USER_EMAIL);
            emailAuth.setString(1,email);

            dbResponse = emailAuth.executeQuery();

            if(dbResponse.next()){
                return dbResponse.getString(1).equals(email);
            }
            else {
                return false;
            }
        }
        finally {
            if(dbResponse != null){
                    dbResponse.close();
            }
            if(emailAuth != null){
                emailAuth.close();
            }
        }
    }
    public static boolean authPassword(String login, String password, Connection connection) throws SQLException, NoSuchAlgorithmException {
        PreparedStatement passAuth = null;
        ResultSet dbResponse = null;
        try{
            passAuth = connection.prepareStatement(DAODbQuery.SQL_GET_USER_PASSWORD);
            passAuth.setString(1, login);
            dbResponse = passAuth.executeQuery();
            if(dbResponse.next()){
                String dbPassword = dbResponse.getString(1);
                String dbSalt = dbResponse.getString(2);
                System.out.println();
                if( Encryptor.getPasswordHashCode(password,dbSalt).equals(dbPassword)){
                    return true;
                }
                else {
                    return false;
                }
            }
            else{
                return false;
            }
        }
        finally {
            if(dbResponse != null){
                dbResponse.close();
            }
            if(passAuth != null){
                passAuth.close();
            }
        }
    }
    public static int getIdByLogin(String login, Connection connection) throws SQLException {
        PreparedStatement getIdStatement = null;
        ResultSet dbResponse = null;
        try{
            getIdStatement = connection.prepareStatement(DAODbQuery.SQL_GET_USER_ID_BY_LOGIN);
            getIdStatement.setString(1,login);
            dbResponse = getIdStatement.executeQuery();
            if (dbResponse.next()){
                return dbResponse.getInt(1);
            }
            else{
                return -1;
            }
        }
        finally {
            if(dbResponse != null){
                dbResponse.close();
            }
            if (getIdStatement != null){
                getIdStatement.close();
            }
        }
    }

}
