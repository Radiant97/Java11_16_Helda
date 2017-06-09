package by.tc.like_it.controller;

import by.tc.like_it.controller.command.Command;
import by.tc.like_it.controller.command.CommandProvider;
import by.tc.like_it.controller.command.config.ParameterName;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Controller(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandString = request.getParameter(ParameterName.COMMAND);
        Command command = CommandProvider.getInstance().getCommand(commandString);
        command.execute(request, response);
    }
}
