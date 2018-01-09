package by.tc.task.controller;

import by.tc.task.controller.command.Command;
import by.tc.task.controller.command.CommandDirector;
import by.tc.task.controller.constant.AttributeKey;
import by.tc.task.controller.constant.CommandParam;

import by.tc.task.controller.constant.ResponseConstruction;
import by.tc.task.exception.ServiceException;
import by.tc.task.service.ServiceFactory;
import by.tc.task.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Y50-70 on 09.11.2017.
 */

public class FrontController extends HttpServlet {

        private ServiceFactory factory = ServiceFactory.getInstance();
        private UserService userService = factory.getUserService();

        private static final Logger logger = LogManager.getLogger(FrontController.class);
    @Override
    public void init() throws ServletException{
        userService.establishConnectionToDb();
    }
    @Override
    public void destroy(){
        userService.destroyConnectionToDb();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(AttributeKey.CONTENT_TYPE);
        String commandType = request.getParameter(CommandParam.COMMAND);
        CommandDirector director = new CommandDirector();
        try{
            Command command = director.getCommand(commandType);
            if(!commandType.equals(CommandParam.CHANGE_LOCALE)){
                String lastRequest = request.getServletPath() + ResponseConstruction.QUESTION_MARK + request.getQueryString();
                response.addCookie(new Cookie(AttributeKey.LAST_REQUEST, lastRequest));
            }
            command.execute(request, response);
        }
        catch (ServiceException e){
            logger.error(e.getMessage() + e.getStackTrace().toString());
            PrintWriter out = response.getWriter();
            out.println("WOOPS... something went wrong");  //чуть позже будет полноценная страница с ошибкой
        }
        }

    }
