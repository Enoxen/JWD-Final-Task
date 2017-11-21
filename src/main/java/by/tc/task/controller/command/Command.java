package by.tc.task.controller.command;


import by.tc.task.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Y50-70 on 19.11.2017.
 */
public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException;
}
