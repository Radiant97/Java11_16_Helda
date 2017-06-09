package by.tc.like_it.controller.command.impl.authorization;

import by.tc.like_it.controller.command.Command;
import by.tc.like_it.controller.command.config.JSPPageName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOutCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            response.sendRedirect(JSPPageName.MAIN_PAGE);
        }
    }
}
