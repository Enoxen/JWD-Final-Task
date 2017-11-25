package by.tc.task.controller.command.help;

import by.tc.task.controller.command.Command;
import by.tc.task.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Y50-70 on 20.11.2017.
 */
public class AfterRegistrationPage implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException,
                                                            ServletException, IOException {

        String command = request.getParameter("command");
            if(command.equals("after_registration")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/finderPage.jsp");
                dispatcher.forward(request, response);
            }
    }
}
