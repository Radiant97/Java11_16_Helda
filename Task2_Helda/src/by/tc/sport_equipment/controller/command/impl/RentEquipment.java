package by.tc.sport_equipment.controller.command.impl;

import by.tc.sport_equipment.bean.Category;
import by.tc.sport_equipment.bean.Equipment;
import by.tc.sport_equipment.bean.User;
import by.tc.sport_equipment.controller.command.Command;
import by.tc.sport_equipment.controller.command.exception.CommandException;
import by.tc.sport_equipment.service.ClientService;
import by.tc.sport_equipment.service.exception.ServiceException;
import by.tc.sport_equipment.service.factory.ServiceFactory;

public class RentEquipment implements Command {
    private final String parametersDelimeter = ",";
    private final int loginNum = 0;
    private final int passwordNum = 1;
    private final int eqTitleNum = 2;
    private final int eqCategoryNum = 3;
    private final int eqPriceNum = 4;

    @Override
    public String execute(String parameters) throws CommandException{
        Equipment equipment;
        String response;

        String[] splittedParameters = parameters.split(parametersDelimeter);

        String login = splittedParameters[loginNum].trim();
        String password = splittedParameters[passwordNum].trim();
        String title = splittedParameters[eqTitleNum].trim();
        Category category = Category.valueOf(splittedParameters[eqCategoryNum].trim().toUpperCase());
        int price = Integer.valueOf(splittedParameters[eqPriceNum].trim());

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);

        equipment = new Equipment();
        equipment.setCategory(category);
        equipment.setPrice(price);
        equipment.setTitle(title);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();
        try {
            clientService.rentEquipment(user, equipment);
            response = "Successful rent.";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return response;
    }
}
