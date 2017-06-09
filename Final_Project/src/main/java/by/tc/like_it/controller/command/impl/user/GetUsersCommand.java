package by.tc.like_it.controller.command.impl.user;

import by.tc.like_it.controller.command.Command;
import by.tc.like_it.controller.command.config.ParameterName;
import by.tc.like_it.controller.command.util.RequestHandler;
import by.tc.like_it.service.UserService;
import by.tc.like_it.service.exception.ServiceException;
import by.tc.like_it.service.exception.ServiceWrongDataException;
import by.tc.like_it.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Ananas on 09.06.2017.
 */
public class GetUsersCommand implements Command{

    private static final Logger LOGGER = Logger.getLogger(BanUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//
//        Locale locale = RequestHandler.getLocale(request);
//
//
//        ServiceFactory serviceFactory = ServiceFactory.getInstance();
//        UserService userService = serviceFactory.getUserService();
//        try {
////            userService.getUsers();
//            response.sendRedirect(RequestHandler.getPreviousPage(request));
//        } catch (ServiceException e) {
//            LOGGER.error(e);
//
////            LocalResourceManager locManager = LocalResourceManager.getInstance();
////            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
////            response.getWriter().print(locManager.getValue(LocalParameter.INTERNAL_SERVER_ERROR,
////                    locale));
//        } catch (ServiceWrongDataException e) {
//            LOGGER.info(e);
//
////            LocalResourceManager locManager = LocalResourceManager.getInstance();
////            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
////            response.getWriter().print(locManager.getValue(LocalParameter.WRONG_PARAM_ERROR,
////                    locale));
//        }
    }
}
