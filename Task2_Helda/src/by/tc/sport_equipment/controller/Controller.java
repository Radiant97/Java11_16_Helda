package by.tc.sport_equipment.controller;

import by.tc.sport_equipment.controller.command.Command;
import by.tc.sport_equipment.controller.command.exception.CommandException;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Controller {
    private final CommandProvider provider = new CommandProvider();
    private final static Logger logger = Logger.getLogger(Controller.class.getName());

    private static final char paramDelimiter = ' ';
    private static final String errorMessage = "WrongRequest.";

    public String executeTask(String request){
        String commandName;
        String parameters;
        Command executionCommand;

        commandName = request.substring(0, request.indexOf(paramDelimiter));
        parameters = request.substring(request.indexOf(paramDelimiter) + 1);
        executionCommand = provider.getCommand(commandName);

        String response;
        try {
            response = executionCommand.execute(parameters);
        } catch (CommandException e){
            response = errorMessage;
            logger.log(Level.SEVERE, errorMessage, e);
        }

        return response;

    }
}
