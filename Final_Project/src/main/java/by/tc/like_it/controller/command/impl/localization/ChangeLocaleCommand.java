package by.tc.like_it.controller.command.impl.localization;

import by.tc.like_it.controller.command.Command;
import by.tc.like_it.controller.command.config.AttributeName;
import by.tc.like_it.controller.command.config.ParameterName;
import by.tc.like_it.controller.command.util.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocaleCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String language = request.getParameter(ParameterName.LANGUAGE);
        if (language != null) {
            request.getSession().setAttribute(AttributeName.LANGUAGE, language);
        }
        response.sendRedirect(RequestHandler.getPreviousPage(request));
    }
}
