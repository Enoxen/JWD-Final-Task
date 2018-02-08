package by.tc.task.dao.help;

import by.tc.task.dao.constant.DAODbQuery;
import by.tc.task.dao.constant.DAOSearchQuery;
import by.tc.task.entity.FilmData;
import by.tc.task.entity.FilmOutput;
import by.tc.task.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Y50-70 on 02.02.2018.
 */
public class SearchHelp {

    public static FilmOutput makeRandomFilm(ResultSet result) throws SQLException {
        if (result.isBeforeFirst()){
            FilmOutput film = new FilmOutput();
            result.next();
            film.setFilmId(result.getInt(1));
//            film.setRating(result.getFloat(2));
//            film.setTitle(result.getString(3));
//            film.setFilmDescription(result.getString(4));
//            film.setGenres(result.getString(5));
//            film.setActors(result.getString(6));
//            film.setDirectors(result.getString(7));
            return film;
        }
        else {
            return null;
        }
    }
    public static String chooseRandomFilmQuery(String locale){
        if (locale.equals("ru")){
            return DAOSearchQuery.SQL_GET_RANDOM_FILM;
        }
        else {
            return DAOSearchQuery.SQL_GET_RANDOM_FILM_EN;
        }
    }
    public static List<FilmOutput> makeFilmData(ResultSet response) throws SQLException {
        if (response.isBeforeFirst()){
            List<FilmOutput> films = new ArrayList<>();
            while(response.next()){
                FilmOutput filmOutput = new FilmOutput();
                filmOutput.setFilmId(response.getInt(1));
                filmOutput.setTitle(response.getString(2));
                filmOutput.setYear(response.getInt(3));
                filmOutput.setRating(response.getFloat(4));
                films.add(filmOutput);
            }
            return films;
        }
        else {
            return null;
        }
    }
    public static int chooseRandomFilmId(Connection connection) throws SQLException {
        List<Integer> filmsId = null;
        try {
            filmsId = new ArrayList<>();
            try (PreparedStatement getIds = connection.prepareStatement(DAODbQuery.SQL_GET_ALL_FILM_ID)) {

                try (ResultSet allId = getIds.executeQuery()) {

                    if (allId.isBeforeFirst()) {
                        while (allId.next()) {
                            filmsId.add(allId.getInt(1));
                        }
                        int randomId = new Random().nextInt(filmsId.size());
                        return filmsId.get(randomId);
                    }
                    else {
                        return -1;
                    }
                }
            }
        }
        finally {
            if (filmsId != null){
                filmsId.clear();
            }
        }
    }
    public static String chooseSearchQueryByRole(Person person, String locale){
        String searchFilmBy;
        if (person.getRole().equals("director")){
            if (locale.equals("ru")) {
                searchFilmBy = DAOSearchQuery.SQL_SEARCH_FILM_BY_DIRECTOR;
            }
            else {
                searchFilmBy = DAOSearchQuery.SQL_SEARCH_FILM_BY_DIRECTOR_EN;
            }
        }
        else{

            if (locale.equals("ru")) {
                searchFilmBy = DAOSearchQuery.SQL_SEARCH_FILM_BY_ACTOR;
            }
            else {
                searchFilmBy = DAOSearchQuery.SQL_SEARCH_FILM_BY_ACTOR_EN;
            }
        }
        return searchFilmBy;
    }
    public static String chooseAdvancedSearchQuery(FilmData filmData, String locale){
        if(locale.equals("ru")){
            if(filmData.getDefaultTitle() == null){
                return DAOSearchQuery.SQL_ADVANCED_SEARCH_NULL_TITLE;
            }
            else {
                return DAOSearchQuery.SQL_ADVANCED_SEARCH_TITLE_NOT_NULL;
            }
        }
        else {
            if(filmData.getDefaultTitle()  == null){
                return DAOSearchQuery.SQL_ADVANCED_SEARCH_NULL_TITLE_EN;
            }
            else {
                return DAOSearchQuery.SQL_ADVANCED_SEARCH_TITLE_NOT_NULL_EN;
            }
        }
    }
}
