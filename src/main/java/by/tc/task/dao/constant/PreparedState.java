package by.tc.task.dao.constant;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public class PreparedState {
    public static final  String AUTHORIZE = "select login from userauth where login = ? and password = ?";
    public static final String REGISTER = "insert into userauth (login, password) values(?,?)";
    public static final String GET_FILM = "select * from films where film_name = ?";
}
