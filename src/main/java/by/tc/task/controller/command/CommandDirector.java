package by.tc.task.controller.command;

import by.tc.task.controller.command.help.*;
import by.tc.task.controller.constant.CommandParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Y50-70 on 19.11.2017.
 */
public class CommandDirector {
    private Map<String,Command> map = new HashMap<>();
    public CommandDirector(){
        map.put(CommandParam.GO_TO_REGISTRATION_PAGE, new UserRegistrator());
        map.put(CommandParam.GO_TO_AUTH_PAGE, new UserAuthorizer());
        map.put(CommandParam.GO_TO_FIND_FILM_PAGE, new FilmGetter());
        map.put(CommandParam.GO_TO_INDEX, new IndexPage());
        map.put(CommandParam.AFTER_REGISTRATION,new AfterRegistrationPage());
        map.put(CommandParam.CHANGE_LOCALE, new ChangeLocale());
        map.put(CommandParam.GO_TO_SIGNING_PAGE, new ToSignPage());
    }
    public Command getCommand(String commandType){
        return  map.get(commandType);
    }
}
