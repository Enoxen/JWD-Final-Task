package by.tc.task.controller.command.help;

import by.tc.task.controller.command.Command;
import by.tc.task.controller.constant.AttributeKey;
import by.tc.task.exception.ServiceException;
import by.tc.task.service.ServiceFactory;
import by.tc.task.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Y50-70 on 19.11.2017.
 */
public class UserRegistrator implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
        response.setContentType("text/html");
        String login = request.getParameter(AttributeKey.LOGIN).trim();
        String password = request.getParameter(AttributeKey.PASSWORD).trim();
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        try {
            userService.registration(login, password);
            response.sendRedirect("FrontController?command=after_registration&message=norm");
        }
        catch (ServiceException e){
            throw new ServiceException(e.getMessage(),e);
        }

    }
}
