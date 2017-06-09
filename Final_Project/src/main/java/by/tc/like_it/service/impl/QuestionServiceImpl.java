package by.tc.like_it.service.impl;

import by.tc.like_it.bean.Question;
import by.tc.like_it.bean.Tag;
import by.tc.like_it.dao.QuestionDAO;
import by.tc.like_it.dao.TagDAO;
import by.tc.like_it.dao.exception.DAOException;
import by.tc.like_it.dao.factory.DAOFactory;
import by.tc.like_it.service.QuestionService;
import by.tc.like_it.service.exception.ServiceException;
import by.tc.like_it.service.exception.ServiceWrongDataException;
import by.tc.like_it.service.util.DataValidator;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class QuestionServiceImpl implements QuestionService{
    @Override
    public Question getQuestionById(int questionId)
            throws ServiceException, ServiceWrongDataException {
        DataValidator.validateId(questionId);

        Question question;
        DAOFactory daoFactory = DAOFactory.getInstance();
        QuestionDAO questionDAO = daoFactory.getQuestionDAO();
        try {
            question = questionDAO.getQuestionById(questionId);
            if (question == null) {
                throw new ServiceWrongDataException("Question with " + questionId + "id doesn't exist.");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return question;
    }

    @Override
    public Question addQuestion(String header, String text, String tagName, int userId, Locale locale)
            throws ServiceException, ServiceWrongDataException {
        DataValidator.validateQuestionHeader(header);
        DataValidator.validateQuestionText(text);
        DataValidator.validateId(userId);

        Question question;
        DAOFactory daoFactory = DAOFactory.getInstance();
        QuestionDAO questionDAO = daoFactory.getQuestionDAO();
        TagDAO tagDAO = daoFactory.getTagDAO();
        try {
            Tag tag;
            Integer tagId = null;
            if (tagName != null && !tagName.isEmpty()) {
                DataValidator.validateTagName(tagName);

                tag = tagDAO.getTagByName(tagName);
                if (tag == null) {
                    tag = tagDAO.addTag(tagName, locale);
                }
                tagId = tag.getId();
            }
            question = questionDAO.addQuestion(header, text, tagId, userId, new Date(), locale);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return question;
    }

    @Override
    public int editQuestion(int questionId, String header, String text, Integer themeId, int userId)
            throws ServiceException, ServiceWrongDataException {
        DataValidator.validateQuestionHeader(header);
        DataValidator.validateQuestionText(text);
        DataValidator.validateId(userId);

        int editedQuestionId;
        DAOFactory daoFactory = DAOFactory.getInstance();
        QuestionDAO questionDAO = daoFactory.getQuestionDAO();
        try {
            editedQuestionId = questionDAO.editQuestion(questionId, header, text, themeId, userId, new Date());
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        if (editedQuestionId == 0) {
            throw new ServiceWrongDataException("Can not edit question.");
        }
        return editedQuestionId;
    }

    @Override
    public boolean deleteQuestion(int questionId) throws ServiceException, ServiceWrongDataException {
        DataValidator.validateId(questionId);

        boolean isDeleted ;
        DAOFactory daoFactory = DAOFactory.getInstance();
        QuestionDAO questionDAO = daoFactory.getQuestionDAO();
        try {
            isDeleted = questionDAO.deleteQuestion(questionId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public List<Question> getQuestions(int startFrom, int count, Locale locale)
            throws ServiceException, ServiceWrongDataException {
        DataValidator.validateStartFromElement(startFrom);
        DataValidator.validateElementCount(count);

        List<Question> questionList;

        DAOFactory daoFactory = DAOFactory.getInstance();
        QuestionDAO questionDAO = daoFactory.getQuestionDAO();
        try {
            questionList = questionDAO.getQuestions(startFrom, count, locale);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return questionList;
    }
}
