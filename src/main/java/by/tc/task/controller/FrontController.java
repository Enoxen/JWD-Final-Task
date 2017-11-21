package by.tc.task.controller;

import by.tc.task.controller.command.CommandDirector;
import by.tc.task.exception.ServiceException;
import by.tc.task.service.ServiceFactory;
import by.tc.task.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Y50-70 on 09.11.2017.
 */

public class FrontController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String command = request.getParameter("command");
        CommandDirector director = new CommandDirector();
        PrintWriter out = response.getWriter();
        out.println(request.getHeader("referer"));
        //out.println(request.getRequestURI().replace("/","") + "?" +  request.getQueryString()+
       // "locale="+request.getParameter("command"));
        try{
            director.getCommand(command).execute(request,response);
        }
        catch (ServiceException e){
            e.printStackTrace();
        }
/*
        try {
               // User user = userService.findUser(name, surname);
                //request.setAttribute("user", user);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/finderPage.jsp");
                requestDispatcher.forward(request, response);
            }
            catch (ServiceException e){
                PrintWriter out = response.getWriter();
                out.println(e.getMessage());
            }*/
        }

    }
