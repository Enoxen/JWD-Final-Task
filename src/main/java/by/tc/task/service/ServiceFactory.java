package by.tc.task.service;

import by.tc.task.service.impl.UserServiceImpl;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public class ServiceFactory {
    private final static ServiceFactory instance = new ServiceFactory();
    private final UserService userService = new UserServiceImpl();

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance;
    }
    public UserService getUserService(){
        return userService;
    }
}
