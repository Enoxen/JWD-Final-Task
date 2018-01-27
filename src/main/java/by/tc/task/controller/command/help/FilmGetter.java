package by.tc.task.controller.command.help;

import by.tc.task.controller.command.Command;
import by.tc.task.controller.constant.AttributeKey;
import by.tc.task.controller.constant.PagePath;
import by.tc.task.entity.FilmData;
import by.tc.task.exception.ServiceException;
import by.tc.task.service.ServiceFactory;
import by.tc.task.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Y50-70 on 19.11.2017.
 */
public class FilmGetter implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        String filmName = request.getParameter(AttributeKey.FILM_TITLE);
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        try {
            FilmData filmData = userService.findFilm(filmName);
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.FILM_PAGE);
            request.setAttribute(AttributeKey.SESSION_FILM, filmData);
            dispatcher.forward(request, response);
        }
        catch (ServiceException e){
            throw new ServiceException(e.getMessage(),e);
        }

    }
}
