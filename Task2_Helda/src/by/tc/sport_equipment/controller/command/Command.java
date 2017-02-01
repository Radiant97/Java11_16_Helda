package by.tc.sport_equipment.controller.command;

import by.tc.sport_equipment.controller.command.exception.CommandException;

public interface Command {
    String execute(String parameters) throws CommandException;
}
