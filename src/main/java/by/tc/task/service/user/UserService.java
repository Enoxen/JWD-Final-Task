package by.tc.task.service.user;

import by.tc.task.entity.AuthUserData;
import by.tc.task.entity.User;
import by.tc.task.service.exception.UserServiceException;

/**
 * Created by Y50-70 on 05.02.2018.
 */
public interface UserService {
    User changePassword(AuthUserData userData) throws UserServiceException;
    User changeLogin(String oldLogin, String newLogin, String confirmPassword) throws UserServiceException;
    User changeEmail(String login, String newEmail, String password) throws UserServiceException;
    void addToFavorite(int filmId, int userId) throws UserServiceException;
    void deleteFromFavorite(int filmId, int userId) throws UserServiceException;
    void rateFilm(int filmId, int filmMark, int userId) throws UserServiceException;
    void deleteReview(int reviewId, int userId) throws UserServiceException;
    void addReview(String text, int filmId, int userId) throws UserServiceException;
    void followUser(int followId, int userId) throws UserServiceException;
    void unfollowUser(int unfollowId, int userId) throws UserServiceException;
}
