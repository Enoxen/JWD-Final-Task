package by.tc.task.service.auth.impl;

import by.tc.task.dao.DAOFactory;
import by.tc.task.dao.exception.AuthDAOException;
import by.tc.task.entity.AuthUserData;
import by.tc.task.entity.User;
import by.tc.task.service.auth.AuthService;
import by.tc.task.service.exception.AuthServiceException;
import by.tc.task.service.validation.AuthValidator;

/**
 * Created by Y50-70 on 05.02.2018.
 */
public class AuthServiceImpl implements AuthService{
    @Override
        public boolean addNewUser(AuthUserData data) throws AuthServiceException {
        if (!AuthValidator.isValidRegistrationData(data.getLogin(), data.getOldPassword(), data.getEmail())){
            return false;
        }
        else {
            try{
                final String userRole = "user";
                DAOFactory.getInstance().getAuthDAO().addNewUser(data.getLogin(), data.getOldPassword(), data.getEmail(), userRole);
                return true;
            } catch (AuthDAOException e) {
                throw new AuthServiceException("registration error",e);
            }
        }
    }

    @Override
    public User authUser(AuthUserData data) throws AuthServiceException {
        if (!AuthValidator.isValidAuthData(data)){
            return null;
        }
        else{
            try{
                return DAOFactory.getInstance().getAuthDAO().authUser(data.getLogin(), data.getOldPassword());
            } catch (AuthDAOException e) {
                throw new AuthServiceException("authorization error",e);
            }
        }
    }
}
