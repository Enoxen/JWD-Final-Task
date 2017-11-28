package by.tc.task.dao;

import by.tc.task.entity.Film;
import by.tc.task.exception.DAOException;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public interface UserDAO {
    boolean registration(String name, String surname)throws DAOException;
    boolean authorization(String login, String password) throws DAOException;
    Film findFilm(String name) throws DAOException;
    void establishConnectionToDb();
    void destroyConnectionToDb();
}
