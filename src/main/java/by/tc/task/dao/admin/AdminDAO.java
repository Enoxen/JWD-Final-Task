package by.tc.task.dao.admin;

import by.tc.task.dao.exception.AdminDAOException;
import by.tc.task.entity.FilmData;
import by.tc.task.entity.Person;

import java.util.List;

/**
 * Created by Y50-70 on 12.01.2018.
 */
public interface AdminDAO {
    void addFilm(FilmData filmData, List<Person> persons)throws AdminDAOException;
    boolean banUser(String userLogin) throws AdminDAOException;
    boolean unBanUser(String userLogin) throws AdminDAOException;
    boolean isFilmExists(FilmData filmData) throws AdminDAOException;
    boolean deleteFilmFromDb(int filmId) throws AdminDAOException;
    boolean giveUserAdminRights(String userLogin) throws AdminDAOException;
    boolean takeAdminRightsFromUser(String userLogin) throws AdminDAOException;
}
