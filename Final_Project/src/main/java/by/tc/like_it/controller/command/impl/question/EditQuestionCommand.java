package by.tc.like_it.controller.command.impl.question;

import by.tc.like_it.bean.User;
import by.tc.like_it.controller.command.Command;
import by.tc.like_it.controller.command.config.AttributeName;
import by.tc.like_it.controller.command.config.JSPPageName;
import by.tc.like_it.controller.command.config.ParameterName;
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

public class EditQuestionCommand implements Command{
    private static final Logger LOGGER = Logger.getLogger(EditQuestionCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int questionId = Integer.parseInt(request.getParameter(ParameterName.QUESTION_ID));
        String themeIdString = request.getParameter(ParameterName.TAG_ID);
        Integer themeId = (themeIdString != "") ? Integer.parseInt(themeIdString) : null;
        int userId = ((User)session.getAttribute(AttributeName.USER)).getId();
        String header = request.getParameter(ParameterName.HEADER);
        String text = request.getParameter(ParameterName.TEXT);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        QuestionService questionService = serviceFactory.getQuestionService();
        try {
            int editedQuestionId = questionService.editQuestion(questionId, header, text, themeId, userId);
            response.sendRedirect(JSPPageName.REDIRECT_TO_QUESTION_PAGE + editedQuestionId);
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (ServiceWrongDataException e) {
            LOGGER.info(e);
            response.sendRedirect(JSPPageName.REDIRECT_TO_QUESTION_PAGE + questionId);
        }
    }
}
