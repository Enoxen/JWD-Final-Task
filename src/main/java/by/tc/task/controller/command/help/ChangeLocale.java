package by.tc.task.controller.command.help;

import by.tc.task.controller.command.Command;
import by.tc.task.controller.constant.AttributeKey;
import by.tc.task.controller.constant.PagePath;
import by.tc.task.exception.ServiceException;
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
        request.getSession().setAttribute(AttributeKey.LOCALE, request.getParameter(AttributeKey.LOCALE));
        Cookie[] cookies = request.getCookies();
        String lastRequest= PagePath.FIND_FILM;
        for(Cookie cookie: cookies){
            if(cookie.getName().equals(AttributeKey.LAST_REQUEST)){
                lastRequest = cookie.getValue();
            }
        }
        response.sendRedirect(lastRequest);
    }
}
