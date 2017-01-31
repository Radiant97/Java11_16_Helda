package by.tc.sport_equipment.controller.command.impl;

import by.tc.sport_equipment.bean.Category;
import by.tc.sport_equipment.bean.Equipment;
import by.tc.sport_equipment.bean.User;
import by.tc.sport_equipment.controller.command.Command;
import by.tc.sport_equipment.service.ClientService;
import by.tc.sport_equipment.service.exception.ServiceException;
import by.tc.sport_equipment.service.factory.ServiceFactory;

public class RentEquipment implements Command {

    private final char paramDelimeter = ' ';
    private final int loginNum = 0;
    private final int passwordNum = 1;
    private final int eqTitleNum = 2;
    private final int eqCategoryNum = 3;
    private final int eqPriceNum = 4;

    @Override
    public String execute(String request) {
        Equipment equipment;

        String response = null;

        String paramsStr = request.substring(request.indexOf(paramDelimeter) + 1, request.length());
        String[] params = paramsStr.split(",");

        String login = params[loginNum].trim();
        String password = params[passwordNum].trim();
        String title = params[eqTitleNum].trim();
        Category category = Category.valueOf(params[eqCategoryNum].trim().toUpperCase());
        int price = Integer.valueOf(params[eqPriceNum].trim());

        User user = new User(login, password);
        equipment = new Equipment(title, price, category);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();
        try {
            clientService.rentEquipment(user, equipment);
            response = "Successful rent.";
        } catch (ServiceException e) {
            // write log
            response = "Error during renting procedure.";
        }

        return response;
    }
}
