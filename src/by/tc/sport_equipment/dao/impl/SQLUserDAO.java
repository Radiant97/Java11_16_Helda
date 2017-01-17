package by.tc.sport_equipment.dao.impl;

import by.tc.sport_equipment.bean.Equipment;
import by.tc.sport_equipment.bean.User;
import by.tc.sport_equipment.dao.UserDAO;
import by.tc.sport_equipment.dao.exception.DAOException;

import java.sql.*;

public class SQLUserDAO implements UserDAO{
    @Override
    public void signIn(String login, String password) throws DAOException{
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

            String query = "SELECT * FROM users WHERE `login`=`"+login+"`";
            rs = st.executeQuery(query);
            rs.next();

            if (rs.getString(3) != password){
                throw  new DAOException("Error during sign in process.");
            }
        }
        catch (SQLException e){
            throw new DAOException("Error during sign in process.", e);
        }
        finally{
            try {
                if (rs != null) { rs.close(); }
                if (st != null) { st.close(); }
                if (con != null) { con.close(); }
            } catch (SQLException e) {
                throw new DAOException("Closing connection error.", e);
            }
        }

    }

    @Override
    public void registration(User user) throws DAOException{
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
            st.executeUpdate("INSERT INTO `users` (`login`, `password`) VALUES ('"+ login + "', '" + password + "');");
        }
        catch (SQLException e){
            throw new DAOException("Error during registration process.", e);
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

    @Override
    public void rentEquipment(User user, Equipment equipment) throws DAOException {
        String login = user.getLogin();
        if (login == null || login.isEmpty()){
            throw new DAOException("Incorrect login.");
        }

        if (equipment.getCategory() == null){ throw new DAOException("Incorrect equipment category");}
        if (equipment.getTitle() == null || equipment.getTitle().isEmpty()){ throw new DAOException("Incorrect equipment title");}

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

            rs = st.executeQuery("SELECT * FROM equipments WHERE `title`=`"+equipment.getTitle()+"`");
            rs.next();
            int equipmentId = rs.getInt(0);

            st.executeUpdate("INSERT INTO `orders` (`user`, `equipment`) VALUES ('" + userId + "', '" + equipmentId + "');");
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
