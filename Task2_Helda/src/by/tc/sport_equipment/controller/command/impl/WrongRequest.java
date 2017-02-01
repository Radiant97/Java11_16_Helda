package by.tc.sport_equipment.controller.command.impl;

import by.tc.sport_equipment.controller.command.Command;

public class WrongRequest implements Command{

    @Override
    public String execute(String parameters) {
        String response = "WrongRequest.";

        return response;
    }
}
