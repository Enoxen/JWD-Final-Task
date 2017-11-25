package by.tc.task.service.impl;

import by.tc.task.dao.DAOFactory;
import by.tc.task.dao.UserDAO;
import by.tc.task.entity.Film;
import by.tc.task.exception.DAOException;
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
    public boolean authorization(String login, String password) throws ServiceException{
        try {
            return userDAO.authorization(login, password);
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }
    }
    @Override
    public boolean registration(String login, String password) throws ServiceException{
            try {
                return userDAO.registration(login, password);
            }
            catch (DAOException e){
                throw new ServiceException(e);
            }

    }

    @Override
    public Film findFilm(String name) throws ServiceException {
        try {
            return userDAO.findFilm(name);
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
