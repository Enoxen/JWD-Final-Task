package by.tc.task.dao.user;


import by.tc.task.dao.exception.AuthDAOException;
import by.tc.task.dao.exception.ChangeDbDataException;
import by.tc.task.dao.exception.ChangeUserDataDAOException;
import by.tc.task.dao.exception.SearchDAOException;
import by.tc.task.entity.FilmOutput;
import by.tc.task.entity.Mark;
import by.tc.task.entity.Review;
import by.tc.task.entity.User;

import java.util.List;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public interface UserDAO {
    User changePassword(String login, String oldPassword, String newPassword) throws ChangeUserDataDAOException;
    User changePassword(String email,String newPassword) throws ChangeUserDataDAOException;
    User changeLogin(String oldLogin, String newLogin, String confirmPassword) throws AuthDAOException;
    User changeEmail(String login, String newEmail, String password) throws AuthDAOException;
    void addToFavorite(int filmId, int userId) throws ChangeDbDataException;
    void deleteFromFavorite(int filmId, int userId) throws ChangeDbDataException;
    void rateFilm(int filmId, int filmMark, int userId) throws ChangeDbDataException;
    void deleteReview(int reviewId, int userId) throws ChangeDbDataException;
    void addReview(String text, int filmId, int userId) throws ChangeDbDataException;
    void followUser(int followId, int userId) throws ChangeUserDataDAOException;
    void unfollowUser(int unfollowId, int userId) throws ChangeUserDataDAOException;
    List<Review> getUserReviews(int userId, int startPos, int amount) throws SearchDAOException;
    List<Mark> getUserMarks(int userId, int startPos, int amount, String locale) throws SearchDAOException;
    List<FilmOutput> getUserFavoriteFilms(int userId, String locale);
}
