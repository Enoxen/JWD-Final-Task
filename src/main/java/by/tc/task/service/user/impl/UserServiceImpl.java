package by.tc.task.service.user.impl;

import by.tc.task.dao.DAOFactory;
import by.tc.task.dao.exception.AuthDAOException;
import by.tc.task.dao.exception.ChangeDbDataException;
import by.tc.task.dao.exception.ChangeUserDataDAOException;
import by.tc.task.dao.user.UserDAO;
import by.tc.task.entity.AuthUserData;
import by.tc.task.entity.User;
import by.tc.task.service.exception.UserServiceException;
import by.tc.task.service.user.UserService;
import by.tc.task.service.validation.AuthValidator;

/**
 * Created by Y50-70 on 05.02.2018.
 */
public class UserServiceImpl implements UserService {

    @Override
    public User changePassword(AuthUserData userData) throws UserServiceException {
        if (userData.getLogin() == null){
            if (!AuthValidator.isValidForgotPasswordData(userData)){
                return null;
            }
            else {
                UserDAO userDAO = DAOFactory.getInstance().getUserDao();
                try {
                   return userDAO.changePassword(userData.getEmail(), userData.getNewPassword());
                } catch (ChangeUserDataDAOException e) {
                    throw new UserServiceException("change password by email service exception",e);
                }
            }
        }
        else {
            if (!AuthValidator.isValidChangePasswordByLoginData(userData)){
                return null;
            }
            else {
                UserDAO userDAO = DAOFactory.getInstance().getUserDao();
                try{
                    return userDAO.changePassword(userData.getLogin(),userData.getOldPassword(),userData.getNewPassword());
                } catch (ChangeUserDataDAOException e) {
                    throw new UserServiceException("change password by login service exception",e);
                }
            }
        }
    }

    @Override
    public User changeLogin(String oldLogin, String newLogin, String confirmPassword) throws UserServiceException {
        if (!AuthValidator.isValidLogin(oldLogin) || !AuthValidator.isValidLogin(newLogin) || !AuthValidator.isValidPassword(confirmPassword)){
            return null;
        }
        else{
            try{
                return DAOFactory.getInstance().getUserDao().changeLogin(oldLogin, newLogin, confirmPassword);
            } catch (AuthDAOException e) {
                throw new UserServiceException("change login service error", e);
            }
        }
    }

    @Override
    public User changeEmail(String login, String newEmail, String password) throws UserServiceException {
        if (!AuthValidator.isValidLogin(login) || !AuthValidator.isValidEmail(newEmail) || !AuthValidator.isValidPassword(password)){
            return null;
        }
        else{
            try {
                return DAOFactory.getInstance().getUserDao().changeEmail(login, newEmail, password);
            } catch (AuthDAOException e) {
                throw new UserServiceException("change email service error",e);
            }
        }
    }

    @Override
    public void addToFavorite(int filmId, int userId) throws UserServiceException {
        try{
            UserDAO userDAO = DAOFactory.getInstance().getUserDao();
            userDAO.addToFavorite(filmId,userId);
        } catch (ChangeDbDataException e) {
            throw new UserServiceException("add to favorite service exception",e);
        }
    }

    @Override
    public void deleteFromFavorite(int filmId, int userId) throws UserServiceException {
        try{
            UserDAO userDAO = DAOFactory.getInstance().getUserDao();
            userDAO.deleteFromFavorite(filmId, userId);
        } catch (ChangeDbDataException e) {
            throw new UserServiceException("delete from favorite service exception",e);
        }
    }

    @Override
    public void rateFilm(int filmId, int filmMark, int userId) throws UserServiceException {
        try{
            if (filmMark > 0 && filmMark <= 10) {
                UserDAO userDAO = DAOFactory.getInstance().getUserDao();
                userDAO.rateFilm(filmId, filmMark, userId);
            }
        } catch (ChangeDbDataException e) {
            throw new UserServiceException("rate film service exception",e);
        }
    }

    @Override
    public void deleteReview(int reviewId, int userId) throws UserServiceException {
        try{
            UserDAO userDAO = DAOFactory.getInstance().getUserDao();
            userDAO.deleteReview(reviewId, userId);
        } catch (ChangeDbDataException e) {
            throw new UserServiceException("delete review service Exception",e);
        }
    }

    @Override
    public void addReview(String text, int filmId, int userId) throws UserServiceException {
        try {
            if (text != null) {
                UserDAO userDAO = DAOFactory.getInstance().getUserDao();
                userDAO.addReview(text, filmId, userId);
            }
        }
        catch (ChangeDbDataException e){
            throw new UserServiceException("add review service exception",e);
        }
    }

    @Override
    public void followUser(int followId, int userId) throws UserServiceException {
        try{
            UserDAO userDAO = DAOFactory.getInstance().getUserDao();
            userDAO.followUser(followId, userId);
        } catch (ChangeUserDataDAOException e) {
            throw new UserServiceException("follow user service exception", e);
        }
    }

    @Override
    public void unfollowUser(int unfollowId, int userId) throws UserServiceException {
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDao();
            userDAO.unfollowUser(unfollowId, userId);
        } catch (ChangeUserDataDAOException e) {
            throw new UserServiceException("unfollow user service exception", e);
        }

    }
}
