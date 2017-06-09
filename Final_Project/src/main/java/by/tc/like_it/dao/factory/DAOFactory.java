package by.tc.like_it.dao.factory;

import by.tc.like_it.dao.*;
import by.tc.like_it.dao.impl.*;

public class DAOFactory {
    private final static  DAOFactory instance = new DAOFactory();

    private DAOFactory() {
    }

    public static DAOFactory getInstance(){
        return instance;
    }
    public UserDAO getUserDAO(){
        return new UserDAOImpl();
    }
    public InitSourceDAO getInitSourceDAO() { return new InitSourceDAOImpl(); }
    public QuestionDAO getQuestionDAO() { return new QuestionDAOImpl(); }
    public AnswerDAO getAnswerDAO() { return new AnswerDAOImpl(); }
    public TagDAO getTagDAO() { return new TagDAOImpl(); }

}
