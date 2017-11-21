package by.tc.task.controller.command.help;

import by.tc.task.controller.command.Command;
import by.tc.task.exception.ServiceException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Y50-70 on 21.11.2017.
 */
public class ChangeLocale implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        request.getSession().setAttribute("locale", request.getParameter("command"));
        response.sendRedirect(request.getRequestURI().replace("/","") + "?" +  request.getQueryString());
    }
}
