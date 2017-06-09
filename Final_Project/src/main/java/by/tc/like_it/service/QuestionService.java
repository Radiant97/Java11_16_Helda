package by.tc.like_it.service;

import by.tc.like_it.bean.Question;
import by.tc.like_it.service.exception.ServiceException;
import by.tc.like_it.service.exception.ServiceWrongDataException;

import java.util.List;
import java.util.Locale;

public interface QuestionService {
    Question getQuestionById(int questionId)
            throws ServiceException, ServiceWrongDataException;
    Question addQuestion(String header, String text, String tag, int userId, Locale locale)
            throws ServiceException, ServiceWrongDataException;
    int editQuestion(int questionId, String header, String text, Integer themeId, int userId)
            throws ServiceException, ServiceWrongDataException;
    boolean deleteQuestion(int questionId) throws ServiceException, ServiceWrongDataException;
    List<Question> getQuestions(int startFrom, int count, Locale locale)
            throws ServiceException, ServiceWrongDataException;
}
