package by.tc.like_it.controller.command.impl.tag;

import by.tc.like_it.bean.Tag;
import by.tc.like_it.controller.command.Command;
import by.tc.like_it.controller.command.config.AttributeName;
import by.tc.like_it.controller.command.config.JSPPageName;
import by.tc.like_it.controller.command.config.ParameterName;
import by.tc.like_it.controller.command.util.RequestHandler;
import by.tc.like_it.service.TagService;
import by.tc.like_it.service.exception.ServiceException;
import by.tc.like_it.service.exception.ServiceWrongDataException;
import by.tc.like_it.service.factory.ServiceFactory;
import by.tc.like_it.util.lacalization.LocalParameter;
import by.tc.like_it.util.lacalization.LocalResourceManager;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetTagsJSON implements Command{
    private static final Logger LOGGER = Logger.getLogger(GetTagsJSON.class);

    private static final String JSON_CONTENT_TYPE = "application/json";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int startFrom = Integer.parseInt(request.getParameter(ParameterName.START_FROM));
        int tagCount = Integer.parseInt(request.getParameter(ParameterName.TAG_COUNT));

        Locale locale = RequestHandler.getLocale(request);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TagService tagService = serviceFactory.getTagService();
        try {
            List<Tag> tagList = tagService.getTags(startFrom, tagCount, locale);
            String json = new Gson().toJson(tagList);
            response.setContentType(JSON_CONTENT_TYPE);
            response.getWriter().print(json);
        } catch (ServiceException e) {
            LOGGER.error(e);

            LocalResourceManager locManager = LocalResourceManager.getInstance();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().print(locManager.getValue(LocalParameter.INTERNAL_SERVER_ERROR,
                    locale));
        } catch (ServiceWrongDataException e) {
            LOGGER.info(e);

            LocalResourceManager locManager = LocalResourceManager.getInstance();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(locManager.getValue(LocalParameter.WRONG_PARAM_ERROR,
                    locale));
        }
    }
}
