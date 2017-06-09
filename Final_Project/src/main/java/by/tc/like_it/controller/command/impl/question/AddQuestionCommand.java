package by.tc.like_it.controller.command.impl.question;

import by.tc.like_it.bean.Question;
import by.tc.like_it.bean.User;
import by.tc.like_it.controller.command.Command;
import by.tc.like_it.controller.command.config.AttributeName;
import by.tc.like_it.controller.command.config.JSPPageName;
import by.tc.like_it.controller.command.config.ParameterName;
import by.tc.like_it.controller.command.util.RequestHandler;
import by.tc.like_it.service.QuestionService;
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

public class AddQuestionCommand implements Command{
    private static final Logger LOGGER = Logger.getLogger(AddQuestionCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale locale = RequestHandler.getLocale(request);
        HttpSession session = request.getSession();

        int userId = ((User)session.getAttribute(AttributeName.USER)).getId();
        String header = request.getParameter(ParameterName.HEADER);
        String text = request.getParameter(ParameterName.TEXT);
        String tag = request.getParameter(ParameterName.TAG);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        QuestionService questionService = serviceFactory.getQuestionService();
        try {
            Question question = questionService.addQuestion(header, text, tag, userId, locale);
            response.sendRedirect(JSPPageName.QUESTION_PAGE + question.getId());
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (ServiceWrongDataException e) {
            LOGGER.info(e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
