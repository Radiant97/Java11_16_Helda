package by.tc.like_it.dao.impl;

import by.tc.like_it.bean.Answer;
import by.tc.like_it.dao.AnswerDAO;
import by.tc.like_it.dao.config.SQLQuery;
import by.tc.like_it.dao.pool.ConnectionPool;
import by.tc.like_it.dao.pool.exception.ConnectionPoolException;
import by.tc.like_it.dao.exception.DAOException;
import by.tc.like_it.util.BeanCreator;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class AnswerDAOImpl implements AnswerDAO{
    @Override
    public List<Answer> getAnswersByQuestionId(int questionId) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Answer> answerList = null;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SQLQuery.GET_ANSWERS_BY_QUESTION_ID_SQL);
            preparedStatement.setInt(1, questionId);
            resultSet = preparedStatement.executeQuery();
            answerList = BeanCreator.createAnswerList(resultSet);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection", e);
        } catch (SQLException e) {
            throw new DAOException("Can't get answers by question id.", e);
        } finally {
            connectionPool.close(connection, preparedStatement, resultSet);
        }
        return answerList;
    }
    @Override
    public Answer addAnswer(String text, Date creationDate, int questionId, int userId)
            throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int answerId = 0;
        Answer answer = null;
        try {
            connection = connectionPool.getConnection();

            ps = connection.prepareStatement(SQLQuery.ADD_ANSWER_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, text);
            ps.setDate(2, new java.sql.Date(creationDate.getTime()));
            ps.setInt(3, questionId);
            ps.setInt(4, userId);
            if (ps.executeUpdate() > 0) {
                rs  = ps.getGeneratedKeys();
                if (rs.next()) {
                    answerId =  rs.getInt(1);
                }
                answer = getAnswerById(answerId);
            }
        }catch (ConnectionPoolException e) {
            throw new DAOException("Can not get a connection", e);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error during addition answer process.", e);
        } finally {
            connectionPool.close(connection, ps, rs);
        }
        return answer;
    }

    @Override
    public Answer getAnswerById(int id) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Answer answer = null;
        try {
            connection = connectionPool.getConnection();

            ps = connection.prepareStatement(SQLQuery.GET_ANSWER_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.next()){
                throw new DAOException("Answer with such id does not exist.");
            }
            answer = BeanCreator.createAnswer(rs);
        }
        catch (SQLException e) {
            throw new DAOException("Error during getting answer by id process.", e);
        } catch (ConnectionPoolException e){
            throw new DAOException("Can not get a connection.", e);
        } finally {
            connectionPool.close(connection, ps, rs);
        }
        return answer;
    }
}
