package by.tc.task.dao.search;

import by.tc.task.dao.exception.SearchDAOException;
import by.tc.task.entity.FilmData;
import by.tc.task.entity.FilmOutput;
import by.tc.task.entity.Person;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by Y50-70 on 12.01.2018.
 */
public interface SearchDAO {
    FilmData getRandomFilm();
    LinkedHashSet<FilmOutput> advancedSearch(FilmData filmData, String locale) throws SearchDAOException;
    List<FilmOutput> searchByPerson(Person person, String locale) throws SearchDAOException;
}
