package by.tc.task.service.admin;

import by.tc.task.dao.exception.AdminDAOException;
import by.tc.task.entity.FilmData;
import by.tc.task.entity.Person;
import by.tc.task.service.exception.AdminServiceException;

import java.util.List;

/**
 * Created by Y50-70 on 07.02.2018.
 */
public interface AdminService {
    void addFilm(FilmData filmData, List<Person> persons) throws AdminServiceException;
    boolean banUser(String userLogin) throws AdminServiceException;
    boolean unBanUser(String userLogin) throws AdminServiceException;
    boolean isFilmExists(FilmData filmData) throws AdminServiceException;
    boolean deleteFilmFromDb(int filmId) throws AdminServiceException;
    boolean giveUserAdminRights(String userLogin) throws AdminServiceException;
    boolean takeAdminRightsFromUser(String userLogin) throws AdminServiceException;
}
