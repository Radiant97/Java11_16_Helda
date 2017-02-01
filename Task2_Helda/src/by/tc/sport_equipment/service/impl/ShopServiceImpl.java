package by.tc.sport_equipment.service.impl;

import by.tc.sport_equipment.bean.Equipment;
import by.tc.sport_equipment.bean.Order;
import by.tc.sport_equipment.bean.User;
import by.tc.sport_equipment.dao.EquipmentDAO;
import by.tc.sport_equipment.dao.UserDAO;
import by.tc.sport_equipment.dao.exception.DAOException;
import by.tc.sport_equipment.dao.factory.DAOFactory;
import by.tc.sport_equipment.service.ShopService;
import by.tc.sport_equipment.service.exception.ServiceException;

public class ShopServiceImpl implements ShopService{
    @Override
    public Order getRentReport(User user) throws ServiceException {
        Order order;

        String login = user.getLogin();
        if (login == null || login.isEmpty()){
            throw new ServiceException("Incorrect login.");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        EquipmentDAO equipmentDAO = daoFactory.getEquipmentDAO();
        try {
            order = equipmentDAO.getRentRecord(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return order;
    }
}
