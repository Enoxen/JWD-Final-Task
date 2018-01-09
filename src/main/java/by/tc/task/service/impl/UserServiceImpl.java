package by.tc.task.service.impl;

import by.tc.task.dao.DAOFactory;
import by.tc.task.dao.UserDAO;
import by.tc.task.entity.Film;
import by.tc.task.exception.DAOException;
import by.tc.task.exception.ServiceException;
import by.tc.task.service.UserService;
import by.tc.task.service.validation.Validator;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public class UserServiceImpl implements UserService {
    private DAOFactory factory = DAOFactory.getInstance();
    private UserDAO userDAO = factory.getUserDao();

    public UserServiceImpl(){}

    public void establishConnectionToDb(){
        userDAO.establishConnectionToDb();
    }

    @Override
    public void destroyConnectionToDb() {
        userDAO.destroyConnectionToDb();
    }

    @Override
    public boolean authorization(String login, String password) throws ServiceException{
        try {
            if(Validator.isValidAuthData(login,password)) {
                return userDAO.authorization(login, password);
            }
            else return false;
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }
    }
    @Override
    public boolean registration(String login, String password) throws ServiceException{
            try {
                if(Validator.isValidAuthData(login, password)) {
                    return userDAO.registration(login, password);
                }
                else{
                    return false;
                }
            }
            catch (DAOException e){
                throw new ServiceException("", e);
            }

    }

    @Override
    public Film findFilm(String name) throws ServiceException {
        try {
            if(Validator.isValidFilmRequest(name)) {
                return userDAO.findFilm(name);
            }
            else {
                return null;
            }
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
