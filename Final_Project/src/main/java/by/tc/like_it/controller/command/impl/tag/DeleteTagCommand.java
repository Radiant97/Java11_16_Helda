package by.tc.like_it.controller.command.impl.tag;

import by.tc.like_it.controller.command.Command;
import by.tc.like_it.controller.command.config.ParameterName;
import by.tc.like_it.service.TagService;
import by.tc.like_it.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTagCommand implements Command{
    private static final Logger LOGGER = Logger.getLogger(DeleteTagCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int themeId = Integer.parseInt(request.getParameter(ParameterName.TAG_ID));

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TagService themeService = serviceFactory.getTagService();
//        try {
//            themeService.deleteTag(themeId);
//            response.sendRedirect(JSPPageName.REDIRECT_TO_THEMES_PAGE);
//        } catch (ServiceException e) {
//            LOGGER.error(e);
//            request.getRequestDispatcher(JSPPageName.ERROR_500_PAGE).forward(request, response);
//        }
    }
}
