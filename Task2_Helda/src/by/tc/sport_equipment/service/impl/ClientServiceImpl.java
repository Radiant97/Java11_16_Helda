package by.tc.sport_equipment.service.impl;

import by.tc.sport_equipment.bean.Equipment;
import by.tc.sport_equipment.bean.User;
import by.tc.sport_equipment.dao.UserDAO;
import by.tc.sport_equipment.dao.exception.DAOException;
import by.tc.sport_equipment.dao.factory.DAOFactory;
import by.tc.sport_equipment.service.ClientService;
import by.tc.sport_equipment.service.exception.ServiceException;

public class ClientServiceImpl implements ClientService {

    @Override
    public void signIn(String login, String password) throws ServiceException {
        if (login == null || login.isEmpty()){
            throw new ServiceException("Incorrect login");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            userDAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void registration(User user) throws ServiceException {
        String login = user.getLogin();
        if (login == null || login.isEmpty()){
            throw new ServiceException("Incorrect login.");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try{
            userDAO.registration(user);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void rentEquipment(User user, Equipment equipment) throws ServiceException {
        String login = user.getLogin();
        if (login == null || login.isEmpty()){
            throw new ServiceException("Incorrect login.");
        }

        if (equipment.getCategory() == null){ throw new ServiceException("Incorrect equipment category");}
        if (equipment.getTitle() == null || equipment.getTitle().isEmpty()){ throw new ServiceException("Incorrect equipment title");}

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            userDAO.rentEquipment(user, equipment);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
