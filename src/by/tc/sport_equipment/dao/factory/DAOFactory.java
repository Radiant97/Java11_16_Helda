package by.tc.sport_equipment.dao.factory;


import by.tc.sport_equipment.dao.EquipmentDAO;
import by.tc.sport_equipment.dao.UserDAO;
import by.tc.sport_equipment.dao.impl.SQLEquipmentDAO;
import by.tc.sport_equipment.dao.impl.SQLUserDAO;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();


    private final EquipmentDAO sqlEquipmentImpl = new SQLEquipmentDAO();
    private final UserDAO sqlUserImpl = new SQLUserDAO();

    private DAOFactory(){}

    public static DAOFactory getInstance() { return  instance; }

    public UserDAO getUserDAO() { return sqlUserImpl; }
    public EquipmentDAO getEquipmentDAO() { return sqlEquipmentImpl; }
}
