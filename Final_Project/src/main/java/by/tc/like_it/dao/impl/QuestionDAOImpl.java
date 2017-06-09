package by.tc.like_it.dao.impl;

import by.tc.like_it.bean.Question;
import by.tc.like_it.dao.QuestionDAO;
import by.tc.like_it.dao.config.SQLQuery;
import by.tc.like_it.dao.pool.ConnectionPool;
import by.tc.like_it.dao.pool.exception.ConnectionPoolException;
import by.tc.like_it.dao.exception.DAOException;
import by.tc.like_it.util.BeanCreator;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class QuestionDAOImpl implements QuestionDAO{
    @Override
    public List<Question> getQuestions(int startFrom, int count, Locale locale)
            throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        String sqlQuery = locale.getLanguage().equals(Locale.ENGLISH.getLanguage()) ?
                SQLQuery.GET_QUESTIONS_FROM_COUNT_EN
                : SQLQuery.GET_QUESTIONS_FROM_COUNT;

        List<Question> questionList = null;
        try {
            connection = connectionPool.getConnection();

            ps = connection.prepareStatement(sqlQuery);
            ps.setInt(1, startFrom);
            ps.setInt(2, count);
            resultSet = ps.executeQuery();

            questionList = BeanCreator.createQuestionList(resultSet);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.close(connection, ps, resultSet);
        }
        return questionList;
    }

    @Override
    public Question getQuestionById(int questionId) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Question question = null;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SQLQuery.GET_QUESTION_BY_ID_SQL);
            preparedStatement.setInt(1, questionId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                question = BeanCreator.createQuestion(resultSet);
            }

        }catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.close(connection, preparedStatement, resultSet);
        }
        return question;
    }

    @Override
    public Question addQuestion(String header, String text, Integer tagId, int userId,
                                Date creationDate, Locale locale) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sqlQuery = locale.getLanguage().equals(Locale.ENGLISH.getLanguage()) ?
                SQLQuery.ADD_QUESTION_EN
                : SQLQuery.ADD_QUESTION;

        int questionId = 0;
        Question question = null;
        try {
            connection = connectionPool.getConnection();

            ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, header);
            ps.setString(2, text);
            ps.setDate(3, new java.sql.Date(creationDate.getTime()));
            ps.setInt(4, userId);
            if (tagId == null) {
                ps.setNull(5, java.sql.Types.INTEGER);
            } else {
                ps.setInt(5, tagId);
            }
            if (ps.executeUpdate() > 0) {
                rs  = ps.getGeneratedKeys();
                if (rs.next()) {
                    questionId =  rs.getInt(1);
                }
                question = getQuestionById(questionId);
            }
        }catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.close(connection, ps, rs);
        }
        return question;
    }

    @Override
    public int editQuestion(int id, String header, String text, Integer tagId, int userId, Date creationDate)
            throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int editedQuestionId = 0;
        try {
            connection = connectionPool.getConnection();

            ps = connection.prepareStatement(SQLQuery.EDIT_QUESTION_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, header);
            ps.setString(2, text);
            ps.setDate(3, new java.sql.Date(creationDate.getTime()));
            if (tagId == null) {
                ps.setNull(4, java.sql.Types.INTEGER);
            } else {
                ps.setInt(4, tagId);
            }
            ps.setInt(5, id);
            if (ps.executeUpdate() > 0) {
                rs  = ps.getGeneratedKeys();
                if (rs.next()) {
                    editedQuestionId =  rs.getInt(1);
                }
            }
        }catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.close(connection, ps, rs);
        }
        return editedQuestionId;
    }

    @Override
    public boolean deleteQuestion(int questionId) throws DAOException {
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement ps = null;

        boolean isDeleted = false;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection=connectionPool.getConnection();

            ps = connection.prepareStatement(SQLQuery.DELETE_QUESTION_SQL);
            ps.setInt(1, questionId);
            if (ps.executeUpdate() == 1) {
                isDeleted = true;
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection",e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.close(connection, ps);
        }
        return isDeleted;
    }

}
