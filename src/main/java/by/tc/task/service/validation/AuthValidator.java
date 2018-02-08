package by.tc.task.service.validation;
import by.tc.task.dao.constant.DAODbQuery;
import by.tc.task.entity.AuthUserData;

import java.util.regex.Pattern;

/**
 * Created by Y50-70 on 25.11.2017.
 */
public class AuthValidator {
    private static final String LOGIN_PATTERN = "^[A-Z][A-Za-z-\\.\\d_]{6,45}|[А-Я][А-Яа-я-\\.\\d_]{6,45}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$ _!%*#?&])[A-Za-z\\d$@$ _!%*#?&]{8,}$";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)?@[A-Za-z0-9]+\\.[A-Za-z]{2,4}$";


    public static boolean isValidAuthData(AuthUserData data){

        return isValidLogin(data.getLogin()) && isValidPassword(data.getOldPassword());
    }
    public static boolean isValidChangePasswordByLoginData(AuthUserData data){

        return isValidLogin(data.getLogin()) && isValidPassword(data.getOldPassword())
                && isValidPassword(data.getNewPassword()) && isValidPassword(data.getPasswordConfirm())
                && data.getNewPassword().equals(data.getPasswordConfirm());
    }
    public static boolean isValidForgotPasswordData(AuthUserData userData){

        return isValidEmail(userData.getEmail()) && isValidPassword(userData.getNewPassword()) &&
                isValidPassword(userData.getPasswordConfirm()) &&
                userData.getNewPassword().equals(userData.getPasswordConfirm());
    }

    public static boolean isValidRegistrationData(String login, String password, String email){
        return isValidLogin(login) && isValidPassword(password) && isValidEmail(email);
    }

    public static boolean isValidPassword(String password){
        return Pattern.matches(PASSWORD_PATTERN, password);
    }

    public static boolean isValidLogin(String login){
        return Pattern.matches(LOGIN_PATTERN, login);
    }

    public static boolean isValidEmail(String email){
        return Pattern.matches(EMAIL_PATTERN,email);
    }
}
