package by.tc.task.controller.command.help;

import by.tc.task.controller.command.Command;
import by.tc.task.controller.constant.AttributeKey;
import by.tc.task.exception.ServiceException;
import by.tc.task.service.ServiceFactory;
import by.tc.task.service.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by Y50-70 on 19.11.2017.
 */
public class UserAuthorizer implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)throws ServiceException{
        response.setContentType("text/html");
        String login = request.getParameter(AttributeKey.LOGIN).trim();
        String password = request.getParameter(AttributeKey.PASSWORD).trim();
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        try {
            if(userService.authorization(login, password)){
                response.sendRedirect("FrontController?command=after_registration&message=norm");
            }
        }
        catch (ServiceException e){
            throw new ServiceException(e.getMessage(),e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
