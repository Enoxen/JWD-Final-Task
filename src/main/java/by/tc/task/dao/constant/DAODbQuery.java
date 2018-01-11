package by.tc.task.dao.constant;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public class DAODbQuery {
    public static final  String SQL_IS_LOGIN_FREE = "select username from user where username = ?";
    public static final String SQL_ADD_NEW_USER = "insert into user (username, email, password, salt, role) values(?,?,?,?,?)";
    public static final String GET_FILM = "select * from films where film_name = ?";
    public static final String SQL_AUTHORIZE_USER_BY_LOGIN = "select username, password, salt, role,user_id from user where (username = ?)";
    public static final String SQL_UPDATE_USER_PASSWORD_BY_LOGIN = "update user set password = ?, salt = ? where username = ?";
    public static final String SQL_GET_USER_PASSWORD = "select password, salt from user where username = ?";
    public static final String SQL_UPDATE_USER_PASSWORD_BY_EMAIL = "update user set password = ? where email = ?";
    public static final String SQL_AUTH_USER_EMAIL = "select email from user where email = ?";
    public static final String SQL_UPDATE_USER_LOGIN = "update user set username = ? where user_id = ?";
    public static final String SQL_GET_USER_ID_BY_LOGIN = "select user_id from user where username = ?";

}
