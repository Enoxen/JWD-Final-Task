package by.tc.task.dao;

import by.tc.task.dao.impl.UserDAOImpl;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final UserDAO userDao = new UserDAOImpl();

    private DAOFactory(){}

    public UserDAO getUserDao(){
        return userDao;
    }
    public static DAOFactory getInstance(){
        return instance;
    }
}

