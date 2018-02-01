package by.tc.task.dao.search.impl;

import by.tc.task.dao.constant.DAODbQuery;
import by.tc.task.dao.datasource.DataSource;
import by.tc.task.dao.exception.SearchDAOException;
import by.tc.task.dao.search.SearchDAO;
import by.tc.task.entity.FilmData;
import by.tc.task.entity.FilmOutput;
import by.tc.task.entity.Person;
import by.tc.task.exception.DataSourceDAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;


/**
 * Created by Y50-70 on 12.01.2018.
 */
public class SearchDAOImpl implements SearchDAO {
    @Override
    public FilmData getRandomFilm() {
        Connection connection = null;
        List<Integer> filmsId = new ArrayList<>();
        try (PreparedStatement getRandomId = connection.prepareStatement(DAODbQuery.SQL_BAN_USER_BY_LOGIN)) {


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LinkedHashSet<FilmOutput> advancedSearch(FilmData filmData, String locale) throws SearchDAOException {
        Connection connection = null;
        LinkedHashSet<FilmOutput> output = new LinkedHashSet<>();
        try {
            connection = DataSource.getConnection();
            List<Integer> temp = filmData.getGenresIn();
            if (temp == null) {
                List<FilmOutput> list = startSearch(filmData, locale, 0, connection);
                if (list != null){
                    output.addAll(list);
                }
            } else {
                for (Integer genreId : temp) {
                    List<FilmOutput> list = startSearch(filmData, locale, genreId,connection);
                    if (list != null){
                        output.addAll(list);
                    }
                }
            }
            return output;
        } catch (DataSourceDAOException | SQLException e) {
            throw new SearchDAOException("without title search error", e);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    private List<FilmOutput> startSearch(FilmData filmData, String locale, int genreId, Connection connection) throws SQLException {
        try (PreparedStatement search = connection.prepareStatement(chooseAdvancedSearchQuery(filmData, locale))) {
            if (filmData.getDefaultTitle() == null) {
                search.setNull(1, Types.NULL);
            } else {
                search.setString(1, filmData.getDefaultTitle());
            }
            search.setInt(2, filmData.getYear());
            search.setInt(3, filmData.getInterval()[0]);
            search.setInt(4, filmData.getInterval()[1]);
            search.setInt(5,genreId);
            System.out.println(search);
            try (ResultSet result = search.executeQuery()) {
                return makeFilmData(result);
            }
        }
    }

    private String chooseAdvancedSearchQuery(FilmData filmData, String locale){
        if(locale.equals("ru")){
            if(filmData.getDefaultTitle() == null){
                return DAODbQuery.SQL_ADVANCED_SEARCH_NULL_TITLE;
            }
            else {
                return DAODbQuery.SQL_ADVANCED_SEARCH_TITLE_NOT_NULL;
            }
        }
        else {
            if(filmData.getDefaultTitle()  == null){
                return DAODbQuery.SQL_ADVANCED_SEARCH_NULL_TITLE_EN;
            }
            else {
                return DAODbQuery.SQL_ADVANCED_SEARCH_TITLE_NOT_NULL_EN;
            }
        }
    }
    @Override
    public List<FilmOutput> searchByPerson(Person person, String locale) throws SearchDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            try (PreparedStatement find = connection.prepareStatement(chooseSearchQueryByRole(person, locale))) {
                find.setString(1, person.getName());
                find.setString(2, person.getSurname());
                try (ResultSet data = find.executeQuery()) {
                    return makeFilmData(data);
                }
            }
        } catch (DataSourceDAOException|SQLException e) {
            throw new SearchDAOException("search by person error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }


    private List<FilmOutput> makeFilmData(ResultSet response) throws SQLException {
        if (response.isBeforeFirst()){
            System.out.println("kek");
            List<FilmOutput> films = new ArrayList<>();
            while(response.next()){
                FilmOutput filmOutput = new FilmOutput();
                filmOutput.setFilmId(response.getInt(1));
                filmOutput.setRating(response.getFloat(2));
                filmOutput.setTitle(response.getString(3));
                filmOutput.setFilmVotes(response.getInt(4));
                filmOutput.setYear(response.getInt(5));
                filmOutput.setFilmDescription(response.getString(6));
                filmOutput.setGenres(response.getString(7));
                filmOutput.setActors(response.getString(8));
                filmOutput.setDirectors(response.getString(9));
                films.add(filmOutput);
            }
            return films;
        }
        else {
            return null;
        }
    }



    private String chooseSearchQueryByRole(Person person, String locale){
        String searchFilmBy;
        if (person.getRole().equals("director")){
            if (locale.equals("ru")) {
                searchFilmBy = DAODbQuery.SQL_SEARCH_FILM_BY_DIRECTOR;
            }
            else {
                searchFilmBy = DAODbQuery.SQL_SEARCH_FILM_BY_DIRECTOR_EN;
            }
        }
        else{

            if (locale.equals("ru")) {
                searchFilmBy = DAODbQuery.SQL_SEARCH_FILM_BY_ACTOR;
            }
            else {
                searchFilmBy = DAODbQuery.SQL_SEARCH_FILM_BY_ACTOR_EN;
            }
        }
        return searchFilmBy;
    }

    private int generateRandomNumber(int start, int end){


        return 0;
    }
}
