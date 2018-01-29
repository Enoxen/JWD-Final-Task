package by.tc.task.dao.search;

import by.tc.task.entity.FilmData;
import by.tc.task.entity.Person;

import java.util.List;

/**
 * Created by Y50-70 on 12.01.2018.
 */
public interface SearchDAO {
    FilmData getRandomFilm();
    List<FilmData> advanceFilmSearch(FilmData filmData);
    List<FilmData> searchByPerson(Person person);
}
