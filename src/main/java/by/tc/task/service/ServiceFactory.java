package by.tc.task.service;


import by.tc.task.dao.DAOFactory;
import by.tc.task.dao.exception.DAOException;
import by.tc.task.service.exception.ServiceException;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public class ServiceFactory {
    private final static ServiceFactory instance = new ServiceFactory();


    private ServiceFactory(){}
    public static void initService()throws ServiceException {
        try{
            DAOFactory.initDataSource();
        } catch (DAOException e) {
            throw new ServiceException("init service error", e);
        }
    }
    public static ServiceFactory getInstance(){
        return instance;
    }

}
