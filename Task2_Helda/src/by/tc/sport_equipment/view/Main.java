package by.tc.sport_equipment.view;


import by.tc.sport_equipment.bean.Category;
import by.tc.sport_equipment.bean.User;
import by.tc.sport_equipment.controller.Controller;
import by.tc.sport_equipment.dao.exception.DAOException;
import by.tc.sport_equipment.dao.impl.SQLEquipmentDAO;
import by.tc.sport_equipment.dao.impl.SQLUserDAO;

public class Main {
   static Category category;
    public static void main(String[] args) {
        /*SQLEquipmentDAO d = new SQLEquipmentDAO();
        d.addEquipment();*/

       // System.out.println(category);
        //String request = "RENT login1, 1";


        String request = "REGISTRATION login1, password1";
        //String request = "SIGN_IN login2, password2";
        Controller controller = new Controller();
        System.out.println(controller.executeTask(request));

        /*char paramDelimeter = ' ';
        String request = "REGISTRATION login1, password1";
        String paramsStr = request.substring(request.indexOf(paramDelimeter) + 1, request.length());
        String[] params = paramsStr.split(",");
        for(String p : params) {
            p = p.trim();
            System.out.println(p);
        }*/

        /*SQLUserDAO u = new SQLUserDAO();
        try{
            User user = new User();
            user.setLogin("123");
            user.setPassword("123");
            u.registration(user);
            //u.signIn("12334", "111111");
            }
            catch (DAOException e) {
            System.out.println(e.getMessage());
        }*/

    }
}
