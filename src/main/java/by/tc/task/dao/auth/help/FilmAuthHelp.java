package by.tc.task.dao.auth.help;

import by.tc.task.dao.constant.DAODbQuery;
import by.tc.task.dao.exception.AuthDAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Y50-70 on 12.01.2018.
 */
public class FilmAuthHelp {
    private static final Logger logger = LogManager.getLogger(FilmAuthHelp.class);
    public static int getFilmIdByTitle(String filmTitle, Connection connection) throws AuthDAOException {
        PreparedStatement filmId = null;
        ResultSet response = null;
        try{
            filmId = connection.prepareStatement(DAODbQuery.SQL_GET_FILM_ID_BY_TITLE);
            response = filmId.executeQuery();
            if(response.next()){
                return response.getInt(1);
            }
            else return -1;
        } catch (SQLException e) {
            throw new AuthDAOException("get film id error",e);
        }
        finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (filmId != null){
                    filmId.close();
                }
            } catch (SQLException e) {
                logger.error(e.getMessage() + " " + e.getStackTrace());
            }
        }
    }
}
