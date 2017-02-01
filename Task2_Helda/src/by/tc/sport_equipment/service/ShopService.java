package by.tc.sport_equipment.service;

import by.tc.sport_equipment.bean.Equipment;
import by.tc.sport_equipment.bean.Order;
import by.tc.sport_equipment.bean.User;
import by.tc.sport_equipment.service.exception.ServiceException;

import java.util.List;

public interface ShopService {
    Order getRentReport(User user) throws ServiceException;
}
