package by.tc.task.service.impl;

import by.tc.task.dao.DAOFactory;
import by.tc.task.dao.user.UserDAO;
import by.tc.task.entity.FilmData;
import by.tc.task.exception.ServiceException;
import by.tc.task.service.UserService;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public class UserServiceImpl implements UserService {
    private DAOFactory factory = DAOFactory.getInstance();
    private UserDAO userDAO = factory.getUserDao();

    public UserServiceImpl(){}


    @Override
    public boolean authorization(String login, String password) throws ServiceException {
        return false;
    }

    @Override
    public boolean registration(String login, String password) throws ServiceException {
        return false;
    }

    @Override
    public FilmData findFilm(String name) throws ServiceException {
        return null;
    }

    @Override
    public void establishConnectionToDb() {

    }

    @Override
    public void destroyConnectionToDb() {

    }
}
