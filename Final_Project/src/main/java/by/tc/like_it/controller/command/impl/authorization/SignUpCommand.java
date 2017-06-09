package by.tc.like_it.controller.command.impl.authorization;

import by.tc.like_it.bean.User;
import by.tc.like_it.controller.command.Command;
import by.tc.like_it.controller.command.config.AttributeName;
import by.tc.like_it.controller.command.config.JSPPageName;
import by.tc.like_it.controller.command.config.ParameterName;
import by.tc.like_it.controller.command.util.RequestHandler;
import by.tc.like_it.service.UserService;
import by.tc.like_it.service.exception.ServiceException;
import by.tc.like_it.service.exception.ServiceWrongDataException;
import by.tc.like_it.service.factory.ServiceFactory;
import by.tc.like_it.util.lacalization.LocalParameter;
import by.tc.like_it.util.lacalization.LocalResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class SignUpCommand implements Command{
    private static final Logger LOGGER = Logger.getLogger(SignUpCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(ParameterName.EMAIL);
        String password = request.getParameter(ParameterName.PASSWORD);
        String confirmPassword = request.getParameter(ParameterName.CONFIRM_PASSWORD);
        String nickname = request.getParameter(ParameterName.NICKNAME);

        HttpSession session = request.getSession();
        Locale locale = RequestHandler.getLocale(request);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        try {
            User user = userService.signUp(email, nickname, password, confirmPassword);
            if (user != null) {
                if (user.isAdmin()) {
                    session.setAttribute(AttributeName.ROLE, AttributeName.ADMIN);
                } else {
                    session.setAttribute(AttributeName.ROLE, AttributeName.USER);
                }
                session.setAttribute(AttributeName.USER, user);
                response.sendRedirect(JSPPageName.MAIN_PAGE);
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (ServiceWrongDataException e) {
            LOGGER.info(e);

            LocalResourceManager locManager = LocalResourceManager.getInstance();
            String message = locManager.getValue(LocalParameter.WRONG_USER_PARAM_ERROR, locale);

            request.setAttribute(AttributeName.STATUS_ERROR, message);
            request.getRequestDispatcher(JSPPageName.SIGN_UP_PAGE).forward(request, response);
        }
    }
}
