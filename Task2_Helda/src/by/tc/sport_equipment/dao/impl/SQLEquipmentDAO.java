package by.tc.sport_equipment.dao.impl;

import by.tc.sport_equipment.bean.Category;
import by.tc.sport_equipment.bean.Equipment;
import by.tc.sport_equipment.bean.Order;
import by.tc.sport_equipment.bean.User;
import by.tc.sport_equipment.dao.EquipmentDAO;
import by.tc.sport_equipment.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLEquipmentDAO implements EquipmentDAO {
    @Override
    public Order getRentRecord(User user) throws DAOException {
        Order order;

        String login = user.getLogin();
        if (login == null || login.isEmpty()){
            throw new DAOException("Incorrect login.");
        }

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/sport_equipment_db",
                    "root",
                    "1111");

            st = con.createStatement();
            String password = user.getPassword();

            rs = st.executeQuery("SELECT * FROM users WHERE `login`=`"+login+"`");
            rs.next();

            if (rs.getString(3) != password){
                throw  new DAOException("Error during sign in process.");
            }
            int userId =  rs.getInt(1);

            rs = st.executeQuery("SELECT * FROM `orders` WHERE `user`=`" + userId + "`;");
            List<Equipment> equipments = new ArrayList<>();
            ResultSet rsEquipment;
            while (rs.next()){
                rsEquipment = st.executeQuery("SELECT * FROM `equipments` WHERE `id`=`" + rs.getInt(1) + "`;");
                Equipment eq = new Equipment(rsEquipment.getString(2), rsEquipment.getInt(3), Category.valueOf(rsEquipment.getString(4).toUpperCase()));
                equipments.add(eq);
            }

            order = new Order(user, equipments);
            return order;
        }
        catch (SQLException e){
            throw new DAOException("Error during renting process.", e);
        }
        finally{
            try {
                if (rs != null) { rs.close(); }
                if (st != null) { st.close(); }
                if (con != null) { con.close(); }
            } catch (SQLException e) {
                throw new DAOException("Closing database connection error.", e);
            }
        }
    }
}
