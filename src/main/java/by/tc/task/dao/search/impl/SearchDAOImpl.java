package by.tc.task.dao.search.impl;


import by.tc.task.dao.constant.DAOSearchQuery;
import by.tc.task.dao.datasource.DataSource;
import by.tc.task.dao.exception.SearchDAOException;
import by.tc.task.dao.help.SearchHelp;
import by.tc.task.dao.search.SearchDAO;
import by.tc.task.entity.FilmData;
import by.tc.task.entity.FilmOutput;
import by.tc.task.entity.Person;
import by.tc.task.dao.exception.DataSourceDAOException;
import by.tc.task.entity.Review;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.List;


/**
 * Created by Y50-70 on 12.01.2018.
 */
public class SearchDAOImpl implements SearchDAO {
    @Override
    public FilmOutput getRandomFilm(String locale) throws SearchDAOException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();

            int randomId = SearchHelp.chooseRandomFilmId(connection);
            try (PreparedStatement getFilm = connection.prepareStatement(SearchHelp.chooseRandomFilmQuery(locale))) {
                getFilm.setInt(1, randomId);
                try (ResultSet result = getFilm.executeQuery()) {
                    return SearchHelp.makeRandomFilm(result);
                }
            }
        } catch (SQLException | DataSourceDAOException e) {
            throw new SearchDAOException("random film error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public LinkedHashSet<FilmOutput> advancedSearch(FilmData filmData, String locale) throws SearchDAOException {
        Connection connection = null;
        LinkedHashSet<FilmOutput> output = new LinkedHashSet<>();
        try {
            connection = DataSource.getConnection();
            List<Integer> temp = filmData.getGenresIn();
            if (temp == null) {
                List<FilmOutput> list = executeAdvancedSearch(filmData, locale, 0, connection);
                if (list != null){
                    output.addAll(list);
                }
            } else {
                for (Integer genreId : temp) {
                    List<FilmOutput> searchResult = executeAdvancedSearch(filmData, locale, genreId,connection);
                    if (searchResult != null){
                        output.addAll(searchResult);
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

    private List<FilmOutput> executeAdvancedSearch(FilmData filmData, String locale, int genreId, Connection connection) throws SQLException {
        try (PreparedStatement search = connection.prepareStatement(SearchHelp.chooseAdvancedSearchQuery(filmData, locale))) {

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
                return SearchHelp.makeFilmData(result);
            }
        }
    }


    @Override
    public List<FilmOutput> searchByPerson(Person person, String locale, int startPos, int amount) throws SearchDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            try (PreparedStatement find = connection.prepareStatement(SearchHelp.chooseSearchQueryByRole(person, locale))) {
                find.setString(1, person.getName());
                find.setString(2, person.getSurname());
                find.setInt(3,startPos);
                find.setInt(4, amount);
                try (ResultSet data = find.executeQuery()) {
                    return SearchHelp.makeFilmData(data);
                }
            }
        } catch (DataSourceDAOException|SQLException e) {
            throw new SearchDAOException("search by person error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public LinkedHashSet<FilmOutput> searchByTitle(String title, String locale, int startPos, int amount) throws SearchDAOException {
        Connection connection = null;
        String query;
        LinkedHashSet<FilmOutput> set = new LinkedHashSet<>();
        if (locale.equals("ru")){
            query = DAOSearchQuery.SQL_SEARCH_FILM_BY_TITLE;
        }
        else{
            query = DAOSearchQuery.SQL_SEARCH_FILM_BY_TITLE_EN;
        }
        try{
            connection = DataSource.getConnection();
            try (PreparedStatement find = connection.prepareStatement(query)) {
                find.setString(1, title);
                find.setInt(2, startPos);
                find.setInt(3,amount);
                try (ResultSet result = find.executeQuery()) {
                    List<FilmOutput> temp = SearchHelp.makeFilmData(result);
                    set.addAll(temp);
                    return set;
                }
            }
        } catch (DataSourceDAOException | SQLException e) {
            throw new SearchDAOException("search by title error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public List<Review> getFilmReviews(int filmId, int startPos, int amount) {
        return null;
    }

}
