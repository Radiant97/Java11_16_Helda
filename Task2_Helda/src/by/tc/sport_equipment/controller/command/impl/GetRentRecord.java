package by.tc.sport_equipment.controller.command.impl;

import by.tc.sport_equipment.bean.Order;
import by.tc.sport_equipment.bean.User;
import by.tc.sport_equipment.controller.command.Command;
import by.tc.sport_equipment.controller.command.exception.CommandException;
import by.tc.sport_equipment.service.ClientService;
import by.tc.sport_equipment.service.ShopService;
import by.tc.sport_equipment.service.exception.ServiceException;
import by.tc.sport_equipment.service.factory.ServiceFactory;

public class GetRentRecord implements Command {
    private final String parametersDelimeter = ",";
    private final int loginNum = 0;
    private final int passwordNum = 1;

    @Override
    public String execute(String parameters) throws CommandException{
        User user;
        String response;

        String[] splittedParameters = parameters.split(parametersDelimeter);

        String login = splittedParameters[loginNum].trim();
        String password = splittedParameters[passwordNum].trim();

        user = new User();
        user.setPassword(password);
        user.setLogin(login);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ShopService shopService = serviceFactory.getShopService();
        try {
            Order order = shopService.getRentReport(user);
            response = "Rent Record: " + order.toString();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return response;
    }

}
