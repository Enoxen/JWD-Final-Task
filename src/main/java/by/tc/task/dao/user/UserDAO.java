package by.tc.task.dao.user;


import by.tc.task.dao.exception.AuthDAOException;
import by.tc.task.dao.exception.ChangeDbDataException;
import by.tc.task.dao.exception.ChangeUserDataDAOException;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public interface UserDAO {
    boolean changePassword(String login,String oldPassword,String newPassword) throws ChangeUserDataDAOException;
    boolean changePassword(String email,String newPassword) throws ChangeUserDataDAOException;
    boolean changeLogin(String oldLogin, String newLogin, String confirmPassword) throws AuthDAOException;
    boolean changeEmail(String login, String newEmail, String password) throws AuthDAOException;
    void addToFavorite(int filmId, int userId) throws ChangeDbDataException;
    void deleteFromFavorite(int filmId, int userId) throws ChangeDbDataException;
    void rateFilm(int filmId, int filmMark, int userId) throws ChangeDbDataException;
    boolean deleteReview(int reviewId, int userId) throws ChangeDbDataException;
    boolean addReview(String text, int filmId, int userId) throws ChangeDbDataException;


    void followUser(int followId, int userId) throws ChangeUserDataDAOException;
    void unfollowUser(int unfollowId, int userId) throws ChangeUserDataDAOException;
}
