package by.tc.like_it.controller.command.impl;

import by.tc.like_it.controller.command.Command;
import by.tc.like_it.controller.command.config.JSPPageName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainPageCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(JSPPageName.MAIN_PAGE);
    }
}
