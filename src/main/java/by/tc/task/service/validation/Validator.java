package by.tc.task.service.validation;

import java.util.regex.Pattern;

/**
 * Created by Y50-70 on 25.11.2017.
 */
public class Validator {
    private static final String LOGIN_PATTERN = "^([A-Za-z]+)|([А-Яа-я]+)$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    private static final String FILM_PATTERN = "^[A-Za-z]|[А-Яа-я]$";
    public static boolean isValidAuthData(String login, String password){

        return Pattern.matches(LOGIN_PATTERN, login) &&
                Pattern.matches(PASSWORD_PATTERN, password);
    }
     public static boolean isValidFilmRequest(String filmTitle){
         return Pattern.matches(FILM_PATTERN, filmTitle);
     }
}
