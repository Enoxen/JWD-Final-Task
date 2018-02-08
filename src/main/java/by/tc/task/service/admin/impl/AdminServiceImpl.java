package by.tc.task.service.admin.impl;

import by.tc.task.dao.DAOFactory;
import by.tc.task.dao.admin.AdminDAO;
import by.tc.task.dao.exception.AdminDAOException;
import by.tc.task.entity.FilmData;
import by.tc.task.entity.Person;
import by.tc.task.service.admin.AdminService;
import by.tc.task.service.exception.AdminServiceException;

import java.util.List;

/**
 * Created by Y50-70 on 07.02.2018.
 */
public class AdminServiceImpl implements AdminService {
    @Override
    public void addFilm(FilmData filmData, List<Person> persons) throws AdminServiceException {
        try {
            AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
            adminDAO.addFilm(filmData, persons);
        } catch (AdminDAOException e) {
            throw new AdminServiceException("add film service error",e);
        }
    }

    @Override
    public boolean banUser(String userLogin) throws AdminServiceException {
        try {
            AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
            return adminDAO.banUser(userLogin);
        } catch (AdminDAOException e) {
            throw new AdminServiceException("ban user service error",e);
        }
    }

    @Override
    public boolean unBanUser(String userLogin) throws AdminServiceException {
        try {
            AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
            return adminDAO.unBanUser(userLogin);
        } catch (AdminDAOException e) {
            throw new AdminServiceException("unban user service error",e);
        }
    }

    @Override
    public boolean isFilmExists(FilmData filmData) throws AdminServiceException {
        try {
            AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
            return adminDAO.isFilmExists(filmData);
        } catch (AdminDAOException e) {
            throw new AdminServiceException("is film exists service error",e);
        }
    }

    @Override
    public boolean deleteFilmFromDb(int filmId) throws AdminServiceException {
        try {
            AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
            return adminDAO.deleteFilmFromDb(filmId);
        } catch (AdminDAOException e) {
            throw new AdminServiceException("delete film from db service error",e);
        }
    }

    @Override
    public boolean giveUserAdminRights(String userLogin) throws AdminServiceException {
        try {
            AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
            return adminDAO.giveUserAdminRights(userLogin);
        } catch (AdminDAOException e) {
            throw new AdminServiceException("give admin rights service error",e);
        }
    }

    @Override
    public boolean takeAdminRightsFromUser(String userLogin) throws AdminServiceException {
        try {
            AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
            return adminDAO.takeAdminRightsFromUser(userLogin);
        } catch (AdminDAOException e) {
            throw new AdminServiceException("get admin rights back service error",e);
        }
    }
}
