package by.tc.task.dao.impl;

import by.tc.task.dao.UserDAO;
import by.tc.task.dao.constant.ConnectionConstant;
import by.tc.task.dao.constant.PreparedState;
import by.tc.task.dao.constant.ResponseFromDb;
import by.tc.task.dao.constant.RequestToDb;
import by.tc.task.entity.Film;
import by.tc.task.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


/**
 * Created by Y50-70 on 12.11.2017.
 */
public class UserDAOImpl implements UserDAO {
    private Connection connection = null;// ошибка при реализации и работе в многопоточное среде
    // в этом случае один коннекшн у тебя будет делиться между выполнением методов параллельными потоками
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);// сначала объявляешь логгер, а потом все остальное

    @Override
    public void establishConnectionToDb() {// а когда ты собираешь вызывать эти методы
        // или как ты собираешься сообщить пользователям твоего кода - как и когда им их надо вызывать
        try {
            Class.forName(ConnectionConstant.DRIVER);
            connection = DriverManager.getConnection(ConnectionConstant.URL, ConnectionConstant.LOGIN,
                    ConnectionConstant.PASSWORD);
        } catch (ClassNotFoundException e) {
            logger.error("Problem with driver registration");

        } catch (SQLException e) {
            logger.error("Problem with connection");
        }
    }

    @Override
    public void destroyConnectionToDb() {
        try{
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            logger.warn("Problems with closing the connection");
        }
    }

    @Override
    public boolean authorization(String login, String password) throws DAOException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    PreparedState.AUTHORIZE);
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
            throw new DAOException("authorization problems", e);
        }
        return false;
    }

    @Override
    public Film findFilm(String name) throws DAOException {
        try {
            PreparedStatement statement = connection.prepareStatement(PreparedState.GET_FILM);
            statement.setString(RequestToDb.FILM_NAME, name);
            ResultSet result = statement.executeQuery();
            return makeFilmFromDbResponse(result);
        } catch (SQLException e) {
            throw new DAOException("problems with finding film", e);
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
            throw new DAOException("problems with making film", e);
        }
    }


    @Override
    public boolean registration(String login, String password) throws DAOException {
        try {
            if (!authorization(login, password)) {
                java.sql.PreparedStatement statement = connection.prepareStatement(PreparedState.REGISTER);
                statement.setString(RequestToDb.USER_LOGIN, login);
                statement.setString(RequestToDb.USER_PASSWORD, password);
                statement.execute();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new DAOException("problems with registration",e);
        }
    }
}
