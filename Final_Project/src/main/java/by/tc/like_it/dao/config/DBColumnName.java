package by.tc.like_it.dao.config;

public class DBColumnName {
    private DBColumnName() {}

    public static final String ID = "id";

    public static final String USER_IS_BANED = "users.is_banned";

    public static final String USER_ID = "users.id";
    public static final String USER_EMAIL = "users.email";
    public static final String USER_PASSWORD = "users.password";
    public static final String USER_NICKNAME = "users.nickname";
    public static final String USER_REAL_NAME = "users.real_name";
    public static final String USER_REGISTRATION_DATE = "users.registration_date";
    public static final String USER_BIRTHDAY = "users.birthday";
    public static final String USER_LOCATION = "users.location";
    public static final String USER_ABOUT_ME = "users.about_me";
    public static final String USER_IS_ADMIN = "users.is_admin";

    public static final String QUESTION_ID = "questions.id";
    public static final String QUESTION_HEADER = "questions.header";
    public static final String QUESTION_TEXT = "questions.text";
    public static final String QUESTION_CREATION_DATE = "questions.creation_date";
    public static final String QUESTION_USER_ID = "questions.user_id";

    public static final String TAG_ID = "tags.id";
    public static final String TAG_NAME = "tags.name";

    public static final String ANSWERS_ID = "answers.id";
    public static final String ANSWERS_TEXT = "answers.text";
    public static final String ANSWERS_CREATION_DATE = "answers.creation_date";
    public static final String ANSWERS_QUESTION_ID = "answers.question_id";
    public static final String ANSWERS_USER_ID = "answers.user_id";
}
