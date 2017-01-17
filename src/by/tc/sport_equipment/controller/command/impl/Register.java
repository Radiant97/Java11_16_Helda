package by.tc.sport_equipment.controller.command.impl;

import by.tc.sport_equipment.bean.User;
import by.tc.sport_equipment.controller.command.Command;
import by.tc.sport_equipment.service.ClientService;
import by.tc.sport_equipment.service.exception.ServiceException;
import by.tc.sport_equipment.service.factory.ServiceFactory;

public class Register implements Command{

    private final char paramDelimeter = ' ';
    private final int loginNum = 0;
    private final int passwordNum = 1;

    @Override
    public String execute(String request) {
        User user;

        String response = null;

        String paramsStr = request.substring(request.indexOf(paramDelimeter) + 1, request.length());
        String[] params = paramsStr.split(",");

        String login = params[loginNum].trim();
        String password = params[passwordNum].trim();

        user = new User(login, password);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();
        try {
            clientService.registration(user);
            response = "Welcome";
        } catch (ServiceException e) {
            // write log
            response = "Error during registration procedure.";
        }

        return response;
    }
}
