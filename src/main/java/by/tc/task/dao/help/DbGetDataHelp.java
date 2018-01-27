package by.tc.task.dao.help;

import by.tc.task.dao.constant.DAODbQuery;
import by.tc.task.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Y50-70 on 23.01.2018.
 */
public class DbGetDataHelp {
    public static int getPersonMaxId(String role, Connection connection) throws SQLException {
        if(role.equals("actor"))
        try (PreparedStatement getId = connection.prepareStatement(DAODbQuery.SQL_GET_MAX_ACTOR_ID)) {
            return getIdByPreparedStatement(getId);
        }
        else{
            try (PreparedStatement getId = connection.prepareStatement(DAODbQuery.SQL_GET_DIRECTOR_MAX_ID)) {
                return getIdByPreparedStatement(getId);
            }
        }
    }


    public static int getIdIfPersonExists(Person person, Connection connection) throws SQLException {
        String getIdStatement;
        if (person.getRole().equals("director")){
            getIdStatement = DAODbQuery.SQL_GET_DIRECTOR_ID_REPEAT_CHECK;
        }
        else {
            getIdStatement = DAODbQuery.SQL_GET_ACTOR_ID_REPEAT_CHECK;
        }
        try (PreparedStatement getId = connection.prepareStatement(getIdStatement)) {
            getId.setString(1, person.getName());
            getId.setString(2, person.getSurname());
            getId.setString(3, person.getNameEn());
            getId.setString(4, person.getSurnameEn());
            return getIdByPreparedStatement(getId);
        }
    }

    public static int getLastFilmId(Connection connection) throws SQLException {
        try (PreparedStatement idStatement = connection.prepareStatement(DAODbQuery.SQL_GET_MAX_FILM_ID)) {
            return getIdByPreparedStatement(idStatement);
        }
    }
    public static int getIdByPreparedStatement(PreparedStatement getId) throws SQLException {
        try (ResultSet id = getId.executeQuery()) {
            if (id.isBeforeFirst()) {
                id.next();
                return id.getInt(1);
            } else {
                return -1;
            }
        }
    }
}
