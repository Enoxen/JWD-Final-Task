package by.tc.task.controller;

import by.tc.task.controller.command.Command;
import by.tc.task.controller.command.CommandDirector;
import by.tc.task.controller.constant.AttributeKey;
import by.tc.task.controller.constant.CommandParam;
import by.tc.task.exception.ServiceException;
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
        /*
        Проблема с томкатом и локализацией была решена, так что моё сообщение на training.by
        более не действительно
         */


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String commandType = request.getParameter(CommandParam.COMMAND);
        CommandDirector director = new CommandDirector();
        try{
            Command command = director.getCommand(commandType);
            if(!commandType.equals(CommandParam.CHANGE_LOCALE)){
                String lastRequest = request.getServletPath() + "?" + request.getQueryString();
                response.addCookie(new Cookie(AttributeKey.LAST_REQUEST, lastRequest));
            }
            command.execute(request, response);
        }
        catch (ServiceException e){
            PrintWriter out = response.getWriter();
            out.println(e.getMessage());
            out.println(e.getStackTrace().toString());
        }
        }

    }
