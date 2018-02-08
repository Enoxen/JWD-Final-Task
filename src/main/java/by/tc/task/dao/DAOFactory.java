package by.tc.task.dao;

import by.tc.task.dao.admin.AdminDAO;
import by.tc.task.dao.admin.impl.AdminDAOImpl;
import by.tc.task.dao.auth.AuthDAO;
import by.tc.task.dao.auth.impl.AuthImpl;
import by.tc.task.dao.datasource.DataSource;
import by.tc.task.dao.exception.DAOException;
import by.tc.task.dao.search.SearchDAO;
import by.tc.task.dao.search.impl.SearchDAOImpl;
import by.tc.task.dao.user.impl.UserDAOImpl;
import by.tc.task.dao.user.UserDAO;
import by.tc.task.dao.exception.DataSourceDAOException;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final UserDAO userDao = new UserDAOImpl();
    private final AuthDAO authDAO = new AuthImpl();
    private final AdminDAO adminDAO = new AdminDAOImpl();
    private final SearchDAO searchDAO = new SearchDAOImpl();



    private DAOFactory(){

    }
    public static DAOFactory getInstance(){
        return instance;
    }

    public static void initDataSource() throws DAOException{
        try {
            DataSource.init();
        } catch (DataSourceDAOException e) {
            throw new DAOException("init data source error",e);
        }
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public AuthDAO getAuthDAO() {
        return authDAO;
    }

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public SearchDAO getSearchDAO() {
        return searchDAO;
    }
}

