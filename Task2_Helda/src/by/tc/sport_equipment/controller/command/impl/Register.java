package by.tc.sport_equipment.controller.command.impl;

import by.tc.sport_equipment.bean.User;
import by.tc.sport_equipment.controller.Controller;
import by.tc.sport_equipment.controller.command.Command;
import by.tc.sport_equipment.controller.command.exception.CommandException;
import by.tc.sport_equipment.service.ClientService;
import by.tc.sport_equipment.service.exception.ServiceException;
import by.tc.sport_equipment.service.factory.ServiceFactory;

public class Register implements Command{
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
        user.setLogin(login);
        user.setPassword(password);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();
        try {
            clientService.registration(user);
            response = "Welcome";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return response;
    }
}
