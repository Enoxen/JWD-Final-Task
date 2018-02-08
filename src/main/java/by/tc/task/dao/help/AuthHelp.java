package by.tc.task.dao.help;

import by.tc.task.dao.constant.DAODbQuery;
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

    public static boolean authEmail(String email, Connection connection) throws SQLException {
        try (PreparedStatement emailAuth = connection.prepareStatement(DAODbQuery.SQL_AUTH_USER_EMAIL)) {
            emailAuth.setString(1, email);
            try (ResultSet dbResponse = emailAuth.executeQuery()) {
                if(dbResponse.isBeforeFirst()){
                    dbResponse.next();
                    return dbResponse.getString(1).equals(email);
                }
                else {
                    return false;
                }
            }
        }
    }
    public static boolean authPassword(String login, String password, Connection connection) throws SQLException, NoSuchAlgorithmException {

        try (PreparedStatement passAuth = connection.prepareStatement(DAODbQuery.SQL_GET_USER_PASSWORD)) {
            passAuth.setString(1, login);
            try (ResultSet dbResponse = passAuth.executeQuery()) {
                if(dbResponse.next()){
                    String dbPassword = dbResponse.getString(1);
                    String dbSalt = dbResponse.getString(2);
                    System.out.println();
                    return Encryptor.getPasswordHashCode(password, dbSalt).equals(dbPassword);
                }
                else{
                    return false;
                }
            }
        }
    }
    public static int getIdByLogin(String login, Connection connection) throws SQLException {

        try (PreparedStatement getIdStatement = connection.prepareStatement(DAODbQuery.SQL_GET_USER_ID_BY_LOGIN)) {
            getIdStatement.setString(1, login);
            return DbGetDataHelp.getIdByPreparedStatement(getIdStatement);
        }


    }

}
