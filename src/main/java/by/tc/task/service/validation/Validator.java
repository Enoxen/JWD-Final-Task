package by.tc.task.service.validation;

import java.util.regex.Pattern;

/**
 * Created by Y50-70 on 25.11.2017.
 */
public class Validator {
    private static final String LOGIN_PATTERN = "^([A-Za-z]+)|([А-Яа-я]+)$";
    private static final String PASSWORD_PATERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    public static boolean isValidAuthData(String login, String password){

        return Pattern.matches(LOGIN_PATTERN, login) &&
                Pattern.matches(PASSWORD_PATERN, password);

    }
}
