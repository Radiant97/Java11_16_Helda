package by.tc.like_it.service;

import by.tc.like_it.bean.Answer;
import by.tc.like_it.service.exception.ServiceException;
import by.tc.like_it.service.exception.ServiceWrongDataException;

import java.util.List;

public interface AnswerService {
    List<Answer> getAnswersByQuestionId(int questionId) throws ServiceException, ServiceWrongDataException;
    Answer addAnswer(String text, int questionId, int userId) throws ServiceException, ServiceWrongDataException;
}
