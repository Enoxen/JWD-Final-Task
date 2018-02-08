package by.tc.task.dao.admin.impl;

import by.tc.task.dao.admin.AdminDAO;
import by.tc.task.dao.constant.DAODbQuery;
import by.tc.task.dao.datasource.DataSource;
import by.tc.task.dao.exception.AdminDAOException;
import by.tc.task.dao.help.DbGetDataHelp;
import by.tc.task.entity.FilmData;
import by.tc.task.entity.Person;
import by.tc.task.dao.exception.DataSourceDAOException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Y50-70 on 12.01.2018.
 */
public class AdminDAOImpl implements AdminDAO {

    @Override
    public void addFilm(FilmData filmData, List<Person> persons) throws AdminDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            connection.setAutoCommit(false);

            int filmId = insertFilmIntoTable(filmData,connection);
            for (Person person : persons){
                insertPersonToDb(person,filmId,connection);
            }

            connection.commit();
            connection.setAutoCommit(true);
        } catch (DataSourceDAOException|SQLException e) {
            throw new AdminDAOException("add filmData error",e);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean banUser(String userLogin) throws AdminDAOException {
        int rowsUpd;
        Connection connection =null;
        try {
            connection = DataSource.getConnection();
            try (PreparedStatement banUser = connection.prepareStatement(DAODbQuery.SQL_BAN_USER_BY_LOGIN)) {

                banUser.setInt(1, 1);
                banUser.setString(2, userLogin);
                rowsUpd = banUser.executeUpdate();
                return rowsUpd != 0;
            }
        }
         catch (DataSourceDAOException| SQLException e) {
            throw new AdminDAOException("error ban user", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean unBanUser(String userLogin) throws AdminDAOException {
        Connection connection = null;
        try{
        connection = DataSource.getConnection();
        int rowsUpd;
        try (PreparedStatement banUser = connection.prepareStatement(DAODbQuery.SQL_BAN_USER_BY_LOGIN)) {
            banUser.setInt(1, 0);
            banUser.setString(2, userLogin);
            rowsUpd = banUser.executeUpdate();
        }
        return rowsUpd != 0;
        } catch (DataSourceDAOException| SQLException e) {
            throw new AdminDAOException("error ban user", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean isFilmExists(FilmData filmData) throws AdminDAOException {
        try {
            Connection connection = DataSource.getConnection();

                try (PreparedStatement checkFilm = connection.prepareStatement(DAODbQuery.SQL_GET_FILM_ID_BY_TITLE)) {
                    checkFilm.setString(1, filmData.getDefaultTitle());
                    checkFilm.setString(2, filmData.getLocalTitle());
                    return DbGetDataHelp.getIdByPreparedStatement(checkFilm) > 0;
            }
        } catch (DataSourceDAOException|SQLException e) {
            throw new AdminDAOException("film check error", e);
        }
    }

    @Override
    public boolean deleteFilmFromDb(int filmId) throws AdminDAOException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            try (PreparedStatement deleteFilm = connection.prepareStatement(DAODbQuery.SQL_CALL_DELETE_FILM_FROM_DB)) {
                deleteFilm.setInt(1, filmId);
                deleteFilm.executeUpdate();
                return true;
            }
        } catch (DataSourceDAOException|SQLException e) {
            throw new AdminDAOException("delete film error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean giveUserAdminRights(String userLogin) throws AdminDAOException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            try (PreparedStatement admin = connection.prepareStatement(DAODbQuery.SQL_GIVE_ADMIN_RIGHTS)) {
                admin.setString(1,userLogin);
                admin.executeUpdate();
                return true;
            }
        } catch (DataSourceDAOException|SQLException e) {
            throw new AdminDAOException("give admin rights error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean takeAdminRightsFromUser(String userLogin) throws AdminDAOException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            try (PreparedStatement admin = connection.prepareStatement(DAODbQuery.SQL_REMOVE_ADMIN_RIGHTS)) {
                admin.setString(1,userLogin);
                admin.executeUpdate();
                return true;
            }
        } catch (DataSourceDAOException|SQLException e) {
            throw new AdminDAOException("give admin rights error", e);
        }
        finally {
            DataSource.closeConnection(connection);
        }
    }


    private boolean insertPersonToDb(Person person, int filmId, Connection connection) throws SQLException {
        String connectStatement;
        String insertStatement;
        if (person.getRole().equals("actor")){
            insertStatement = DAODbQuery.SQL_INSERT_ACTOR_INTO_TABLE;
            connectStatement = DAODbQuery.SQL_CONNECT_ACTOR_WITH_FILM;
        }
        else {
            insertStatement = DAODbQuery.SQL_INSERT_DIRECTOR_INTO_TABLE;
            connectStatement = DAODbQuery.SQL_CONNECT_DIRECTOR_WITH_A_FILM;
        }
        int repeatCheckId = DbGetDataHelp.getIdIfPersonExists(person, connection);
        if (repeatCheckId != -1){
            int rowsUpd;
            try (PreparedStatement connectToFilm = connection.prepareStatement(connectStatement)) {
                connectToFilm.setInt(1,repeatCheckId);
                connectToFilm.setInt(2,filmId);
                rowsUpd = connectToFilm.executeUpdate();
            }
            return rowsUpd != 0;
        }
        else {
            int rowUpd;
            try (PreparedStatement insertPerson = connection.prepareStatement(insertStatement)) {
                insertPerson.setString(1, person.getName());
                insertPerson.setString(2, person.getSurname());
                insertPerson.setString(3, person.getNameEn());
                insertPerson.setString(4, person.getSurnameEn());
                rowUpd = insertPerson.executeUpdate();
                int newPersonId = DbGetDataHelp.getPersonMaxId(person.getRole(),connection);
                int connectUpd;
                try (PreparedStatement connectToFilm = connection.prepareStatement(connectStatement)) {
                    connectToFilm.setInt(1,newPersonId);
                    connectToFilm.setInt(2,filmId);
                    connectUpd = connectToFilm.executeUpdate();
                }
                return rowUpd != 0 && connectUpd !=0;
            }
        }
    }

    private int insertFilmIntoTable(FilmData filmData, Connection connection) throws SQLException {
        try (PreparedStatement addFilm = connection.prepareStatement(DAODbQuery.SQL_INSERT_FILM_DATA_INTO_TABLE)) {

            addFilm.setString(1, filmData.getDefaultTitle());
            addFilm.setString(2, filmData.getDefaultFilmDescription());
            addFilm.setInt(3, filmData.getYear());
            addFilm.setString(4, filmData.getLocalTitle());
            addFilm.setString(5, filmData.getLocalFilmDescription());
            Calendar calendar = Calendar.getInstance();
            Date filmDate = new Date(calendar.getTime().getTime());
            addFilm.setDate(6,filmDate);

            addFilm.executeUpdate();
            int lastFilmId = DbGetDataHelp.getLastFilmId(connection);
            insertFilmGenres(filmData, lastFilmId, connection);
            return lastFilmId;
        }

    }
    private boolean insertFilmGenres(FilmData filmData, int filmId, Connection connection) throws SQLException {
        int[] rowsUpd;
        try (PreparedStatement insertGenres = connection.prepareStatement(DAODbQuery.SQL_CONNECT_FILM_WITH_GENRE)) {
            List<Integer> genresId = filmData.getGenresIn();
            for(Integer id: genresId){
                insertGenres.setInt(1,filmId);
                insertGenres.setInt(2,id);
                insertGenres.addBatch();
            }
            rowsUpd = insertGenres.executeBatch();
        }
        return rowsUpd.length != 0;
    }
}
