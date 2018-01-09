package by.tc.task.controller.command.help;

import by.tc.task.controller.command.Command;
import by.tc.task.controller.constant.AttributeKey;
import by.tc.task.controller.constant.ResponseConstruction;
import by.tc.task.exception.ServiceException;
import by.tc.task.service.ServiceFactory;
import by.tc.task.service.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Y50-70 on 19.11.2017.
 */
public class UserAuthorizer implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)throws ServiceException{
        response.setContentType(AttributeKey.CONTENT_TYPE);
        String login = request.getParameter(AttributeKey.LOGIN).trim();
        String password = request.getParameter(AttributeKey.PASSWORD).trim();
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        try {
            if(userService.authorization(login, password)){
                response.sendRedirect(ResponseConstruction.FRONT_CONTROLLER +
                    ResponseConstruction.QUESTION_MARK +
                    ResponseConstruction.COMMAND +
                    ResponseConstruction.EQUALS +
                    ResponseConstruction.AFTER_REGISTRATION +
                    ResponseConstruction.COMMAND_DELIMETER);
            }
        }
        catch (ServiceException e){
            throw new ServiceException(e.getMessage(),e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
