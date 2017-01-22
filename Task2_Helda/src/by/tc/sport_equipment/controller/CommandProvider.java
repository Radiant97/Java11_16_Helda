package by.tc.sport_equipment.controller;

import by.tc.sport_equipment.controller.command.Command;
import by.tc.sport_equipment.controller.command.CommandName;
import by.tc.sport_equipment.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider(){
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.REGISTRATION, new Register());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
        repository.put(CommandName.RENT, new RentEquipment());
        repository.put(CommandName.GET_RENT_RECORD, new GetRentRecord());
    }

    Command getCommand(String name){
        CommandName commandName;
        Command command;

        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }catch(IllegalArgumentException | NullPointerException e){
            command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }
}
