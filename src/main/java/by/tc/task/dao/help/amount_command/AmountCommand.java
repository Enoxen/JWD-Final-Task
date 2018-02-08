package by.tc.task.dao.help.amount_command;

import by.tc.task.entity.FilmData;

import java.sql.Connection;

/**
 * Created by Y50-70 on 05.02.2018.
 */
public interface AmountCommand {
    int execute(String ofData, FilmData data,String locale, Connection connection);
}
