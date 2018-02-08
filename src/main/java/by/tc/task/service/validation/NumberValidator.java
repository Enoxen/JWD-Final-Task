package by.tc.task.service.validation;

/**
 * Created by Y50-70 on 05.02.2018.
 */
public class NumberValidator {
    public static boolean isValidMark(int filmMark){
        return filmMark > 0 && filmMark <= 10;
    }
}
