package by.tc.sport_equipment.dao;

import by.tc.sport_equipment.bean.Equipment;
import by.tc.sport_equipment.bean.User;
import by.tc.sport_equipment.dao.exception.DAOException;

public interface UserDAO {
    void signIn(String login, String password) throws DAOException;
    void registration(User user) throws DAOException;
    void rentEquipment(User user, Equipment equipment) throws DAOException;
}
