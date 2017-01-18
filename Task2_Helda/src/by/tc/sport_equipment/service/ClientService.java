package by.tc.sport_equipment.service;

import by.tc.sport_equipment.bean.Equipment;
import by.tc.sport_equipment.bean.User;
import by.tc.sport_equipment.service.exception.ServiceException;

public interface ClientService {
    void signIn(String login, String password) throws ServiceException;
    void registration(User user) throws ServiceException;
    void rentEquipment(User user, Equipment equipment) throws ServiceException;
}
