package by.tc.task.controller.command.help;

import by.tc.task.controller.command.Command;
import by.tc.task.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Y50-70 on 21.11.2017.
 */
public class ChangeLocale implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        request.getSession().setAttribute("locale", request.getParameter("locale"));
        Cookie[] cookies = request.getCookies();
        String lastRequest= "after_registration";
        for(Cookie cookie: cookies){
            if(cookie.getName().equals("last_request")){
                lastRequest = cookie.getValue();
            }
        }
        response.sendRedirect(lastRequest);
    }
}
