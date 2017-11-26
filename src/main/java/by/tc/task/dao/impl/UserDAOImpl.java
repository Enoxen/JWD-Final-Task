package by.tc.task.dao.impl;

import by.tc.task.dao.UserDAO;
import by.tc.task.dao.constant.ConnectionConstant;
import by.tc.task.dao.constant.PreparedStatement;
import by.tc.task.dao.constant.ResponseFromDb;
import by.tc.task.dao.constant.RequestToDb;
import by.tc.task.entity.Film;
import by.tc.task.exception.DAOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public class UserDAOImpl implements UserDAO {
    private Connection connection = null;

    @Override
    public boolean authorization(String login, String password) throws DAOException {
        try {
            establishConnection();
            java.sql.PreparedStatement statement = connection.prepareStatement(
                    PreparedStatement.AUTHORIZE);
            statement.setString(RequestToDb.USER_LOGIN, login);
            statement.setString(RequestToDb.USER_PASSWORD, password);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                if (result.getString(ResponseFromDb.USER_LOGIN) == null) {
                    return false;
                } else {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Film findFilm(String name) throws DAOException {
        try {
            establishConnection();
            java.sql.PreparedStatement statement = connection.prepareStatement(PreparedStatement.GET_FILM);
            statement.setString(RequestToDb.FILM_NAME, name);
            ResultSet result = statement.executeQuery();
            return makeFilmFromDbResponse(result);
        } catch (SQLException e) {
            throw new DAOException("problems with finding film", e);
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            }
            catch (SQLException e){
                throw new DAOException(e);
            }
        }
    }

    private Film makeFilmFromDbResponse(ResultSet result) throws DAOException {
        Film film = new Film();
        try {
            while (result.next()) {
                film.setName(result.getString(ResponseFromDb.FILM_NAME));
                film.setGenre(result.getString(ResponseFromDb.FILM_GENRE));
            }
            return film;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return film;

    }


    @Override
    public boolean registration(String login, String password) throws DAOException {
        try {
            if (!authorization(login, password)) {
                establishConnection();
                java.sql.PreparedStatement statement = connection.prepareStatement(PreparedStatement.REGISTER);
                statement.setString(RequestToDb.USER_LOGIN, login);
                statement.setString(RequestToDb.USER_PASSWORD, password);
                statement.execute();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new DAOException("problems with registration",e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private void establishConnection() throws DAOException {
        try {
                if(connection == null) {
                    Class.forName(ConnectionConstant.DRIVER);
                    connection = DriverManager.getConnection(ConnectionConstant.URL,
                            ConnectionConstant.LOGIN, ConnectionConstant.PASSWORD);
                }
                else if (connection.isClosed()){
                    connection = DriverManager.getConnection(ConnectionConstant.URL,
                            ConnectionConstant.LOGIN, ConnectionConstant.PASSWORD);
                }
        } catch (SQLException e) {
            throw new DAOException("no connection to db");
        } catch (ClassNotFoundException e) {
            throw new DAOException("problems with db", e);
        }
    }
}