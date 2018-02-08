package by.tc.task.dao.user.impl;
import by.tc.task.dao.exception.*;
import by.tc.task.dao.help.AuthHelp;
import by.tc.task.dao.constant.DAODbQuery;
import by.tc.task.dao.datasource.DataSource;
import by.tc.task.dao.user.UserDAO;
import by.tc.task.dao.util.Encryptor;
import by.tc.task.entity.FilmOutput;
import by.tc.task.entity.Mark;
import by.tc.task.entity.Review;
import by.tc.task.entity.User;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Y50-70 on 12.11.2017.
 */
public class UserDAOImpl implements UserDAO {



    @Override
    public User changePassword(String login, String oldPassword, String newPassword) throws ChangeUserDataDAOException {
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
                    if (rowsUpdate != 0){
                        return getUpdatedUser(login,connection);
                    }
                    else {
                        return null;
                    }
                }
            }
            else{
                return null;
            }
        } catch (DataSourceDAOException|SQLException|NoSuchAlgorithmException e) {
            throw new ChangeUserDataDAOException("error while changing password",e);
        }
        finally {
            DataSource.closeConnection(connection);

        }
    }

    @Override
    public User changePassword(String email, String newPassword) throws ChangeUserDataDAOException {
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
                if (rowsUpdate != 0){
                    return getUpdatedUser(email,connection);
                }
                else {
                    return null;
                }
            }
            else{
                return null;
            }
        } catch (DataSourceDAOException|SQLException e) {
           throw new ChangeUserDataDAOException("error when changing password by email",e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public User changeLogin(String oldLogin, String newLogin, String confirmPassword) throws AuthDAOException {
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
                    if (rowsUpdate != 0){
                        return getUpdatedUser(newLogin, connection);
                    }
                    else {
                        return null;
                    }
                }
            }
            else {
                return null;
            }
        } catch (DataSourceDAOException|SQLException|NoSuchAlgorithmException e) {
            throw new AuthDAOException("change login error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public User changeEmail(String login, String newEmail, String password) throws AuthDAOException {
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
                if (rowsUpdate != 0){
                    return getUpdatedUser(login, connection);
                }
                else {
                    return null;
                }
            }
            else{
                return null;
            }
        } catch (SQLException|NoSuchAlgorithmException|DataSourceDAOException e) {
            throw new AuthDAOException("change email error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }
    private User getUpdatedUser(String login, Connection connection) throws SQLException {
        try (PreparedStatement updUser = connection.prepareStatement(DAODbQuery.SQL_GET_UPDATED_USER_DATA)) {
            updUser.setString(1, login);
            updUser.setString(2,login);
            try (ResultSet resultSet = updUser.executeQuery()) {
                if (resultSet.isBeforeFirst()){
                    resultSet.next();
                    User user = new User();
                    user.setUserId(resultSet.getInt(1));
                    user.setLogin(resultSet.getString(2));
                    user.setEmail(resultSet.getString(3));
                    user.setRole(resultSet.getString(4));
                    user.setBanned(resultSet.getBoolean(5));
                    return user;
                }
                else {
                    return null;
                }
            }
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
    public List<Review> getUserReviews(int userId, int startPos, int amount) throws SearchDAOException {
        Connection connection = null;
        List<Review> reviewsList = null;
        try{
            connection = DataSource.getConnection();
            try (PreparedStatement getReviews = connection.prepareStatement(DAODbQuery.SQL_GET_USER_REVIEWS)) {
                getReviews.setInt(1,userId);
                getReviews.setInt(2,startPos);
                getReviews.setInt(3,amount);
                try (ResultSet allReviews = getReviews.executeQuery()) {
                    if (allReviews.isBeforeFirst()){
                        reviewsList = new ArrayList<>();
                        while (allReviews.next()){
                            Review review = new Review();
                            review.setReviewId(allReviews.getInt(1));
                            review.setFilmId(allReviews.getInt(2));
                            review.setReviewText(allReviews.getString(3));
                            reviewsList.add(review);
                        }
                    }
                    return reviewsList;
                }
            }
        } catch (DataSourceDAOException | SQLException e) {
            throw new SearchDAOException("get user reviews dao error",e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public List<Mark> getUserMarks(int userId, int startPos, int amount, String locale) throws SearchDAOException {
        Connection connection = null;
        List<Mark> markList = null;
        String localQuery;
        if (locale.equals("ru")){
            localQuery = DAODbQuery.SQL_GET_USER_MARKS;
        }
        else {
            localQuery = DAODbQuery.SQL_GET_USER_MARKS_EN;
        }
        try{
            connection = DataSource.getConnection();
            try (PreparedStatement getMarks = connection.prepareStatement(localQuery)) {

                getMarks.setInt(1, userId);
                getMarks.setInt(2, startPos);
                getMarks.setInt(3,amount);

                try (ResultSet marks = getMarks.executeQuery()) {

                    if (marks.isBeforeFirst()){
                        markList = new ArrayList<>();
                        while (marks.next()){
                            Mark mark = new Mark();
                            mark.setFilmId(marks.getInt(1));
                            mark.setFilmMark(marks.getInt(2));
                            mark.setUserId(marks.getInt(3));
                            mark.setFilmTitle(marks.getString(4));
                            markList.add(mark);
                        }
                    }
                    return markList;
                }
            }
        } catch (DataSourceDAOException | SQLException e) {
            throw new SearchDAOException("get user marks dao error",e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public List<FilmOutput> getUserFavoriteFilms(int userId, String locale) {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();

        } catch (DataSourceDAOException e) {
            e.printStackTrace();
        }
        return null;
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
    public void deleteReview(int reviewId, int userId) throws ChangeDbDataException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            PreparedStatement delete = connection.prepareStatement(DAODbQuery.SQL_DELETE_USER_REVIEW);
            delete.setInt(1,reviewId);
        } catch (DataSourceDAOException| SQLException e) {
            throw new ChangeDbDataException("delete review error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public void addReview(String text, int filmId, int userId) throws ChangeDbDataException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            try (PreparedStatement addReview = connection.prepareStatement(DAODbQuery.SQL_ADD_REVIEW)) {
                addReview.setString(1, text);
                addReview.setInt(2, filmId);
                addReview.setInt(3,userId);
                addReview.executeUpdate();
            }
        } catch (DataSourceDAOException|SQLException e) {
            throw new ChangeDbDataException("add review error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }
}