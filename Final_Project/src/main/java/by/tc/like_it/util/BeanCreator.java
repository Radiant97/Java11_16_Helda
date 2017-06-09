package by.tc.like_it.util;

import by.tc.like_it.bean.Answer;
import by.tc.like_it.bean.Question;
import by.tc.like_it.bean.Tag;
import by.tc.like_it.bean.User;
import by.tc.like_it.dao.config.DBColumnName;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BeanCreator {
    private BeanCreator() {}

    public static User createUser(int id, String email, String nickname,
                                  String password,
                                  String location, String realName) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setRealName(realName);
        user.setRegistrationDate(new Date());
        user.setLocation(location);
        user.setAdmin(false);


        return user;
    }

    public static User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(DBColumnName.USER_ID));
        user.setEmail(resultSet.getString(DBColumnName.USER_EMAIL));
        user.setPassword(resultSet.getString(DBColumnName.USER_PASSWORD));
        user.setNickname(resultSet.getString(DBColumnName.USER_NICKNAME));
        user.setRealName(resultSet.getString(DBColumnName.USER_REAL_NAME));
        user.setRegistrationDate(resultSet.getDate(DBColumnName.USER_REGISTRATION_DATE));
        user.setLocation(resultSet.getString(DBColumnName.USER_LOCATION));
        user.setAdmin(resultSet.getBoolean(DBColumnName.USER_IS_ADMIN));
        user.setBaned(resultSet.getBoolean(DBColumnName.USER_IS_BANED));

        return user;
    }

    public static Question createQuestion(ResultSet resultSet) throws SQLException {
        Question question = new Question();
        question.setId(resultSet.getInt(DBColumnName.QUESTION_ID));
        question.setHeader(resultSet.getString(DBColumnName.QUESTION_HEADER));
        question.setText(resultSet.getString(DBColumnName.QUESTION_TEXT));
        question.setCreationDate(resultSet.getDate(DBColumnName.QUESTION_CREATION_DATE));

        Tag tag = createTag(resultSet);
        question.setTag(tag);

        User user = createUser(resultSet);
        question.setUser(user);

        return question;
    }

    public static Tag createTag(ResultSet resultSet) throws SQLException {
        Tag tag = new Tag();
        tag.setId(resultSet.getInt(DBColumnName.TAG_ID));
        tag.setName(resultSet.getString(DBColumnName.TAG_NAME));

        return tag;
    }

    public static Answer createAnswer(ResultSet resultSet) throws SQLException {
        Answer answer = new Answer();
        answer.setId(resultSet.getInt(DBColumnName.ANSWERS_ID));
        answer.setText(resultSet.getString(DBColumnName.ANSWERS_TEXT));
        answer.setCreation_date(resultSet.getDate(DBColumnName.ANSWERS_CREATION_DATE));

        Question question = createQuestion(resultSet);
        answer.setQuestion(question);

        User user = createUser(resultSet);
        answer.setUser(user);

        return answer;
    }

    public static List<Tag> createTagList(ResultSet resultSet)
            throws SQLException {
        List<Tag> tagList =  new ArrayList<>();
        while (resultSet.next()) {
            Tag tag = createTag(resultSet);
            tagList.add(tag);
        }
        return tagList;
    }
    public static List<Answer> createAnswerList(ResultSet resultSet)
            throws SQLException {
        List<Answer> answerList =  new ArrayList<>();
        while (resultSet.next()) {
            Answer answer = createAnswer(resultSet);
            answerList.add(answer);
        }
        return answerList;
    }
    public static List<Question> createQuestionList(ResultSet resultSet)
            throws SQLException {
        List<Question> questionList =  new ArrayList<>();
        while (resultSet.next()) {
            Question question = BeanCreator.createQuestion(resultSet);
            questionList.add(question);
        }
        return questionList;
    }
    public static List<User> createUserList(ResultSet resultSet)
        throws SQLException {
        List<User> userList =  new ArrayList<>();
        while (resultSet.next()) {
            User user = BeanCreator.createUser(resultSet);
            userList.add(user);
        }
        return userList;
    }
}
