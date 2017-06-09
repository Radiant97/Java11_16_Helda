package by.tc.like_it.dao;

import by.tc.like_it.bean.Answer;
import by.tc.like_it.dao.exception.DAOException;

import java.util.Date;
import java.util.List;

public interface AnswerDAO {
    List<Answer> getAnswersByQuestionId(int questionId) throws DAOException;
    Answer addAnswer(String text, Date careationDate, int questionId, int userId)
            throws DAOException ;
    Answer getAnswerById(int id) throws DAOException;
}
