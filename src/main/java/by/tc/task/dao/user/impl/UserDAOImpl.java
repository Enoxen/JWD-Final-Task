package by.tc.task.dao.user.impl;
import by.tc.task.dao.exception.ChangeDbDataException;
import by.tc.task.dao.help.AuthHelp;
import by.tc.task.dao.constant.DAODbQuery;
import by.tc.task.dao.datasource.DataSource;
import by.tc.task.dao.exception.AuthDAOException;
import by.tc.task.dao.exception.ChangeUserDataDAOException;
import by.tc.task.dao.user.UserDAO;
import by.tc.task.dao.util.Encryptor;
import by.tc.task.exception.DataSourceDAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
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
        try{
            connection = DataSource.getConnection();

            if(AuthHelp.authPassword(login,oldPassword,connection)) {
                int rowsUpdate;
                try (PreparedStatement updatePassword = connection.prepareStatement(DAODbQuery.SQL_UPDATE_USER_PASSWORD_BY_LOGIN)) {

                    String salt = Encryptor.generateSalt();
                    String hashedPassword = Encryptor.getPasswordHashCode(newPassword, salt);

                    updatePassword.setString(1, hashedPassword);
                    updatePassword.setString(2, salt);
                    updatePassword.setString(3, login);

                    rowsUpdate = updatePassword.executeUpdate();
                }
                return rowsUpdate != 0;
            }
            else return false;
        } catch (DataSourceDAOException|SQLException|NoSuchAlgorithmException e) {
            throw new ChangeUserDataDAOException("error while changing password",e);
        }
        finally {
            DataSource.closeConnection(connection);

        }
    }

    @Override
    public boolean changePassword(String email, String newPassword) throws ChangeUserDataDAOException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();

            if(AuthHelp.authEmail(email,connection)){
                int rowsUpdate;
                try (PreparedStatement updatePassword = connection.prepareStatement(DAODbQuery.SQL_UPDATE_USER_PASSWORD_BY_EMAIL)) {
                    updatePassword.setString(1, email);
                    updatePassword.setString(2, newPassword);
                    rowsUpdate = updatePassword.executeUpdate();
                }
                return rowsUpdate != 0;
            }
            else{
                return false;
            }
        } catch (DataSourceDAOException|SQLException e) {
           throw new ChangeUserDataDAOException("error when changing password by email",e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean changeLogin(String oldLogin, String newLogin, String confirmPassword) throws AuthDAOException {
        Connection connection = null;

        try{
            connection = DataSource.getConnection();
            if(AuthHelp.authPassword(oldLogin, confirmPassword, connection)){

                int rowsUpdate;
                try (PreparedStatement updateLogin = connection.prepareStatement(DAODbQuery.SQL_UPDATE_USER_LOGIN)) {

                    updateLogin.setString(1, newLogin);
                    int userId = AuthHelp.getIdByLogin(oldLogin, connection);
                    updateLogin.setInt(2, userId);

                    rowsUpdate = updateLogin.executeUpdate();
                }
                return rowsUpdate != 0;
            }
            else {
                return false;
            }
        } catch (DataSourceDAOException|SQLException|NoSuchAlgorithmException e) {
            throw new AuthDAOException("change login error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean changeEmail(String login, String newEmail, String password) throws AuthDAOException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            if(AuthHelp.authPassword(login,password,connection)){
                int rowsUpdate;
                try (PreparedStatement updateEmail = connection.prepareStatement(DAODbQuery.SQL_UPDATE_USER_EMAIL_BY_LOGIN)) {
                    updateEmail.setString(1, newEmail);
                    updateEmail.setString(2, login);
                    rowsUpdate = updateEmail.executeUpdate();
                }
                return rowsUpdate != 0;
            }
            else{
                return false;
            }
        } catch (SQLException|NoSuchAlgorithmException|DataSourceDAOException e) {
            throw new AuthDAOException("change email error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public void addToFavorite(int filmId, int userId) throws ChangeDbDataException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            try (PreparedStatement favorite = connection.prepareStatement(DAODbQuery.SQL_ADD_FILM_TO_FAVORITE)) {
                favorite.setInt(1, filmId);
                favorite.setInt(2, userId);
                favorite.executeUpdate();
            }
        } catch (DataSourceDAOException|SQLException e) {
            throw new ChangeDbDataException("add to favorite error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public void deleteFromFavorite(int filmId, int userId) throws ChangeDbDataException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            try (PreparedStatement delete = connection.prepareStatement(DAODbQuery.SQL_DELETE_FROM_FAVORITE)) {
                delete.setInt(1, userId);
                delete.setInt(2, filmId);
                delete.executeUpdate();
            }
        } catch (DataSourceDAOException| SQLException e) {
            throw new ChangeDbDataException("delete film from favorite error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public void followUser(int followId, int userId) throws ChangeUserDataDAOException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            try (PreparedStatement follow = connection.prepareStatement(DAODbQuery.SQL_FOLLOW_USER)) {
                follow .setInt(1, userId);
                follow.setInt(2, followId);
                follow.executeUpdate();
            }
        } catch (DataSourceDAOException|SQLException e) {
            throw new ChangeUserDataDAOException("follow error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public void unfollowUser(int unfollowId, int userId) throws ChangeUserDataDAOException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            PreparedStatement unfollow = connection.prepareStatement(DAODbQuery.SQL_UNFOLLOW_USER);
            unfollow.setInt(1, userId);
            unfollow.setInt(2,unfollowId);
            unfollow.executeUpdate();
        } catch (DataSourceDAOException|SQLException e) {
            throw new ChangeUserDataDAOException("unfollow error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public void rateFilm(int filmId, int filmMark, int userId) throws ChangeDbDataException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            try (CallableStatement rateCall = connection.prepareCall(DAODbQuery.SQL_CALL_RECOUNT_RATING)) {
                rateCall.setInt(1, filmMark);
                rateCall.setInt(2, 30);
                rateCall.setInt(3, filmId);
                rateCall.setInt(4,userId);
                rateCall.execute();
            }
        } catch (SQLException| DataSourceDAOException e) {
            throw new ChangeDbDataException("rating count error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean deleteReview(int reviewId, int userId) throws ChangeDbDataException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            PreparedStatement delete = connection.prepareStatement(DAODbQuery.SQL_DELETE_USER_REVIEW);
            delete.setInt(1,reviewId);
            return delete.executeUpdate() != 0;
        } catch (DataSourceDAOException| SQLException e) {
            throw new ChangeDbDataException("delete review error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean addReview(String text, int filmId, int userId) throws ChangeDbDataException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            try (PreparedStatement addReview = connection.prepareStatement(DAODbQuery.SQL_ADD_REVIEW)) {
                addReview.setString(1, text);
                addReview.setInt(2, filmId);
                addReview.setInt(3,userId);
                addReview.executeUpdate();
                return true;
            }
        } catch (DataSourceDAOException|SQLException e) {
            throw new ChangeDbDataException("add review error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }
}