package by.tc.like_it.service.impl;


import by.tc.like_it.bean.Answer;
import by.tc.like_it.dao.AnswerDAO;
import by.tc.like_it.dao.exception.DAOException;
import by.tc.like_it.dao.factory.DAOFactory;
import by.tc.like_it.service.AnswerService;
import by.tc.like_it.service.exception.ServiceException;
import by.tc.like_it.service.exception.ServiceWrongDataException;
import by.tc.like_it.service.util.DataValidator;

import java.util.Date;
import java.util.List;

public class AnswerServiceImpl implements AnswerService{
    @Override
    public List<Answer> getAnswersByQuestionId(int questionId)
            throws ServiceException, ServiceWrongDataException {
        DataValidator.validateId(questionId);

        List<Answer> answerList;
        DAOFactory daoFactory = DAOFactory.getInstance();
        AnswerDAO answerDAO = daoFactory.getAnswerDAO();
        try {
            answerList = answerDAO.getAnswersByQuestionId(questionId);
        } catch (DAOException e){
            throw new ServiceException("Error during getting answers by question id.", e);
        }
        return answerList;
    }

    @Override
    public Answer addAnswer(String text, int questionId, int userId)
            throws ServiceException, ServiceWrongDataException {
        DataValidator.validateAnswerText(text);
        DataValidator.validateId(questionId);
        DataValidator.validateId(userId);

        Answer answer;
        DAOFactory daoFactory = DAOFactory.getInstance();
        AnswerDAO answerDAO = daoFactory.getAnswerDAO();
        try {
            answer = answerDAO.addAnswer(text, new Date(), questionId, userId);
        }catch (DAOException e){
            throw new ServiceException("Can not add answer.", e);
        }
        if (answer == null) {
            throw new ServiceException("Can not add answer.");
        }
        return answer;
    }
}
