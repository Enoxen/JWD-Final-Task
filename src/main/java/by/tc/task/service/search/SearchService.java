package by.tc.task.service.search;

import by.tc.task.dao.exception.SearchDAOException;
import by.tc.task.entity.FilmData;
import by.tc.task.entity.FilmOutput;
import by.tc.task.entity.Person;
import by.tc.task.entity.Review;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by Y50-70 on 07.02.2018.
 */
public interface SearchService {
    FilmOutput getRandomFilm(String locale) throws SearchDAOException;
    LinkedHashSet<FilmOutput> advancedSearch(FilmData filmData, String locale, int startPos, int amount);
    List<FilmOutput> searchByPerson(Person person, String locale, int startPos, int amount);
    LinkedHashSet<FilmOutput> searchByTitle(String title, String locale, int startPos, int amount);
    List<Review> getFilmReviews(int filmId, int startPos, int amount);
    List<Review> getUserReviews(int userId, int startPos, int amount);
    List<FilmOutput> getUserMarks(int userId, int startPos, int amount);
}
