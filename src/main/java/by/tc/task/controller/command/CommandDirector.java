package by.tc.task.controller.command;

import by.tc.task.controller.command.help.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Y50-70 on 19.11.2017.
 */
public class CommandDirector {
    private Map<String,Command> map = new HashMap<>();
    public CommandDirector(){
        map.put("registration", new UserRegistrator());
        map.put("authorization", new UserAuthorizer());
        map.put("find_film", new FilmGetter());
        map.put("after_registration",new AfterRegistrationPage());
        map.put("change_locale", new ChangeLocale());
        map.put("sign", new ToSignPage());
    }
    public Command getCommand(String commandType){
        return  map.get(commandType);
    }
}
