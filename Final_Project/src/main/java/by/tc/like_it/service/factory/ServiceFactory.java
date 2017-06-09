package by.tc.like_it.service.factory;

import by.tc.like_it.service.*;
import by.tc.like_it.service.impl.*;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
    public UserService getUserService() {
        return new UserServiceImpl();
    }
    public InitSourceService getInitSourceService() {
        return new InitSourceServiceImpl();
    }
    public QuestionService getQuestionService() { return new QuestionServiceImpl(); }
    public AnswerService getAnswerService() { return new AnswerServiceImpl(); }
    public TagService getTagService() { return new TagServiceImpl(); }
}
