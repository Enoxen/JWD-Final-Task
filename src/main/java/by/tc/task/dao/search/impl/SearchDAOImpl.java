package by.tc.task.dao.search.impl;

import by.tc.task.dao.constant.DAODbQuery;
import by.tc.task.dao.search.SearchDAO;
import by.tc.task.entity.FilmData;
import by.tc.task.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<FilmData> advanceFilmSearch(FilmData filmData) {
        return null;
    }

    @Override
    public List<FilmData> searchByPerson(Person person) {
        return null;
    }

    private int generateRandomNumber(int start, int end){


        return 0;
    }
}
