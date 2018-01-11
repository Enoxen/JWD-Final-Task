package by.tc.task.dao.auth.impl;

import by.tc.task.dao.auth.AuthDAO;
import by.tc.task.dao.constant.DAODbQuery;
import by.tc.task.dao.datasource.DataSource;
import by.tc.task.dao.exception.AuthDAOException;
import by.tc.task.dao.user.impl.UserDAOImpl;
import by.tc.task.dao.util.Encryptor;
import by.tc.task.entity.AuthUserData;
import by.tc.task.exception.DataSourceDAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Y50-70 on 10.01.2018.
 */
public class AuthImpl implements AuthDAO {
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);
    @Override
    public boolean addNewUser(String login, String password, String email, String role) throws AuthDAOException {
        Connection connection = null;

        try{
            connection = DataSource.getConnection();
            if(isLoginFree(login, connection)) {

                PreparedStatement addUser = connection.prepareStatement(DAODbQuery.SQL_ADD_NEW_USER);

                String salt = Encryptor.generateSalt();

                addUser.setString(1, login);
                addUser.setString(2, email);
                addUser.setString(3, Encryptor.getPasswordHashCode(password, salt));
                addUser.setString(4, salt);
                addUser.setString(5,role);
                int rowsUser = addUser.executeUpdate();
                return rowsUser != 0;
            }
            else {
                return false;
            }

        } catch (SQLException | DataSourceDAOException | NoSuchAlgorithmException e) {
            throw new AuthDAOException("adding new user to db error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }
    private boolean isLoginFree(String login, Connection connection) throws AuthDAOException {
        ResultSet selectResult = null;
        try{
            PreparedStatement isFree = connection.prepareStatement(DAODbQuery.SQL_IS_LOGIN_FREE);
            isFree.setString(1,login);
            selectResult = isFree.executeQuery();
            if(selectResult.next()) {
                String str = selectResult.getString(1);
                System.out.println(str);
                return  str == null;
            }
            else {
                return true;
            }

        } catch (SQLException e) {
            throw new AuthDAOException("login db validation error", e);
        }
        finally {
            try {
                if(selectResult != null){
                    selectResult.close();
                }
            } catch (SQLException e) {
                logger.error("problems with closing ResultSet");
            }
        }
    }
    @Override
    public AuthUserData authUser(String login, String password) throws AuthDAOException {
        Connection connection = null;
        PreparedStatement authUser = null;
        ResultSet authDataFromDb = null;
        try {
            connection = DataSource.getConnection();
            authUser = connection.prepareStatement(DAODbQuery.SQL_AUTHORIZE_USER_BY_LOGIN);

            authUser.setString(1, login);
            authDataFromDb = authUser.executeQuery();

            if(authDataFromDb.next()){

                String dbLogin = authDataFromDb.getString(1);
                String dbPassword = authDataFromDb.getString(2);
                String dbSalt = authDataFromDb.getString(3);
                String dbRole = authDataFromDb.getString(4);
                int dbUserId = authDataFromDb.getInt(5);

                if(login.equals(dbLogin) && Encryptor.getPasswordHashCode(password,dbSalt).equals(dbPassword)){
                    return makeAuthDataFromDbResponse(dbLogin,dbRole,dbUserId);
                }
                else {
                    return null;
                }
            }
            else{
                return null;
            }
        } catch (DataSourceDAOException|SQLException|NoSuchAlgorithmException e) {
            throw new AuthDAOException("user auth problems", e);
        }
        finally {
            DataSource.closeConnection(connection);
            if(authDataFromDb != null){
                try {
                    authDataFromDb.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage() + "DaoAuth.authUser()");
                }
            }
            if(authUser != null){
                try {
                    authUser.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage() + "DaoAuth.authUser()");
                }
            }
        }
    }

    private AuthUserData makeAuthDataFromDbResponse(String dbLogin, String dbRole, int dbUserId){
        AuthUserData authData = new AuthUserData();
        authData.setLogin(dbLogin);
        authData.setRole(dbRole);
        authData.setUserId(dbUserId);
        return authData;
    }
}
