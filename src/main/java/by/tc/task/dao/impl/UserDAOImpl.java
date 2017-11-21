package by.tc.task.dao.impl;

import by.tc.task.dao.UserDAO;
import static by.tc.task.dao.constants.ConnectionConstants.*;
import  by.tc.task.dao.constants.PreparedStatements;
import  by.tc.task.dao.constants.ResponseFromDb;
import  by.tc.task.dao.constants.RequestToDb;
import by.tc.task.entity.Film;
import by.tc.task.exception.DAOException;
import java.sql.*;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public class UserDAOImpl implements UserDAO {
    private Connection connection = null;


    @Override
    public boolean authorization(String login, String password) throws DAOException {
        try {
            establishConnection();
            PreparedStatement statement = connection.prepareStatement(PreparedStatements.AUTHORIZE);
            statement.setString(RequestToDb.USER_LOGIN, login);
            statement.setString(RequestToDb.USER_PASSWORD, password);
            ResultSet result = statement.executeQuery();
            result.next();
            if (result.getString(ResponseFromDb.USER_LOGIN) != null) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
            PreparedStatement statement = connection.prepareStatement(PreparedStatements.GET_FILM);
            statement.setString(RequestToDb.FILM_NAME, name);
            ResultSet result = statement.executeQuery();
            return makeFilmFromDbResponse(result);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            }
            catch (SQLException e){
                throw new DAOException(e.getMessage(),e);
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
                PreparedStatement statement = connection.prepareStatement(PreparedStatements.REGISTER);
                statement.setString(RequestToDb.USER_LOGIN, login);
                statement.setString(RequestToDb.USER_PASSWORD, password);
                statement.execute();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
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
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new DAOException("нет соединения с базой данных");
        } catch (ClassNotFoundException e) {
            throw new DAOException("problems with db", e);
        }
    }
}