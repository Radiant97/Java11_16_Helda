package by.tc.sport_equipment.controller.command.impl;

import by.tc.sport_equipment.controller.command.Command;
import by.tc.sport_equipment.service.ClientService;
import by.tc.sport_equipment.service.exception.ServiceException;
import by.tc.sport_equipment.service.factory.ServiceFactory;

public class SignIn implements Command{

    private final char paramDelimeter = ' ';
    private final int loginNum = 0;
    private final int passwordNum = 1;

    @Override
    public String execute(String request) {
        String login = null;
        String password = null;

        String response = null;

        String paramsStr = request.substring(request.indexOf(paramDelimeter) + 1, request.length());
        String[] params = paramsStr.split(",");

        login = params[loginNum].trim();
        password = params[passwordNum].trim();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();
        try {
            clientService.signIn(login, password);
            response = "Welcome";
        } catch (ServiceException e) {
            // write log
            response = "Error during sign in procedure";
        }

        return response;
    }
}
