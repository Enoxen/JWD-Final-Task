package by.tc.task.service;

import by.tc.task.entity.Film;
import by.tc.task.exception.ServiceException;


/**
 * Created by Y50-70 on 12.11.2017.
 */
public interface UserService {
    boolean authorization(String login, String password) throws ServiceException;
    boolean registration(String login, String password) throws ServiceException;
    Film findFilm(String name) throws ServiceException;

}
