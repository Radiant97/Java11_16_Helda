package by.tc.sport_equipment.dao;

import by.tc.sport_equipment.bean.Equipment;
import by.tc.sport_equipment.bean.Order;
import by.tc.sport_equipment.bean.User;
import by.tc.sport_equipment.dao.exception.DAOException;

public interface EquipmentDAO {
    Order getRentRecord(User user) throws  DAOException;
}
