package by.tc.task.dao;

import by.tc.task.dao.admin.AdminDAO;
import by.tc.task.dao.admin.impl.AdminDAOImpl;
import by.tc.task.dao.auth.AuthDAO;
import by.tc.task.dao.auth.impl.AuthImpl;
import by.tc.task.dao.datasource.DataSource;
import by.tc.task.dao.user.impl.UserDAOImpl;
import by.tc.task.dao.user.UserDAO;
import by.tc.task.exception.DataSourceDAOException;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final UserDAO userDao = new UserDAOImpl();
    private final AuthDAO authDAO = new AuthImpl();
    private final AdminDAO adminDAO = new AdminDAOImpl();



    private DAOFactory(){

    }


    public UserDAO getUserDao(){
        return userDao;
    }
    public static DAOFactory getInstance(){
        return instance;
    }
    public AuthDAO getAuthDAO(){return authDAO;}
    public static void initDataSource(){
        try {
            DataSource.init();
        } catch (DataSourceDAOException e) {
            e.printStackTrace();
        }
    }
    public AdminDAO getAdminDAO() {
        return adminDAO;
    }
}

