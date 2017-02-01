package by.tc.sport_equipment.view;

import by.tc.sport_equipment.controller.Controller;

public class Main {
    public static void main(String[] args) {
        String request = "REGISTRATION login1, password1";
        //String request = "SIGN_IN login2, password2";
        Controller controller = new Controller();
        System.out.println(controller.executeTask(request));
    }
}
