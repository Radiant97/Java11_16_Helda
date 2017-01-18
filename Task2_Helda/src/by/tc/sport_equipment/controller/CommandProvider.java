package by.tc.sport_equipment.controller;

import by.tc.sport_equipment.controller.command.Command;
import by.tc.sport_equipment.controller.command.CommandName;
import by.tc.sport_equipment.controller.command.impl.Register;
import by.tc.sport_equipment.controller.command.impl.RentEquipment;
import by.tc.sport_equipment.controller.command.impl.SignIn;
import by.tc.sport_equipment.controller.command.impl.WrongRequest;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider(){
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.REGISTRATION, new Register());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
        repository.put(CommandName.RENT, new RentEquipment());
    }

    Command getCommand(String name){
        CommandName commandName =null;
        Command command = null;

        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }catch(IllegalArgumentException | NullPointerException e){
            //write log
            command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }
}
