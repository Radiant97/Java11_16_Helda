package by.tc.like_it.dao;

import by.tc.like_it.bean.Question;
import by.tc.like_it.dao.exception.DAOException;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public interface QuestionDAO {
    List<Question> getQuestions(int startFrom, int count, Locale locale)
            throws DAOException;
    Question getQuestionById(int questionId) throws DAOException;
    Question addQuestion(String header, String text, Integer tag, int userId,
                    Date creationDate, Locale locale) throws DAOException;
    int editQuestion(int id, String header, String text, Integer themeId, int userId, Date creationDate)
            throws DAOException;
    boolean deleteQuestion(int questionId) throws DAOException;
}
