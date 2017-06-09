package by.tc.like_it.dao.config;

public class SQLQuery {
    private SQLQuery() {}

    public static final String BANN_USER = "UPDATE users SET is_banned = 1 WHERE id = ?";
    public static final String UN_BANN_USER = "UPDATE users SET is_banned = 0 WHERE id = ?";

    public static final String GET_QUESTIONS_FROM_COUNT = "SELECT * FROM questions " +
            "LEFT JOIN users ON questions.user_id = users.id " +
            "LEFT JOIN tags ON questions.tag_id = tags.id " +
            "WHERE questions.language = 'ru' AND questions.is_deleted = 0 LIMIT ?, ?";
    public static final String GET_QUESTIONS_FROM_COUNT_EN = "SELECT * FROM questions " +
            "LEFT JOIN users ON questions.user_id = users.id " +
            "LEFT JOIN tags ON questions.tag_id = tags.id " +
            "WHERE questions.language = 'en' AND questions.is_deleted = 0 LIMIT ?, ?";


    public static final String GET_QUESTION_BY_ID_SQL = "SELECT * FROM questions " +
            "LEFT JOIN users ON questions.user_id = users.id LEFT JOIN tags " +
            "ON questions.tag_id = tags.id WHERE questions.id = ?";

    public static final String ADD_QUESTION = "INSERT INTO questions " +
            "(header, text, creation_date, user_id, tag_id, language) " +
            "VALUES(?,?,?,?,?, 'ru')";
    public static final String ADD_QUESTION_EN = "INSERT INTO questions " +
            "(header, text, creation_date, user_id, tag_id, language) " +
            "VALUES(?,?,?,?,?, 'en')";

    public static final String DELETE_QUESTION_SQL = "UPDATE questions " +
            "SET is_deleted = 1 WHERE id = ?";
    public static final String EDIT_QUESTION_SQL = "UPDATE questions " +
            "SET header = ?, test = ?, creation_date = ?, theme_id = ? " +
            "WHERE id = ?";

    public static final String GET_ANSWERS_BY_QUESTION_ID_SQL = "SELECT * FROM answers " +
            "JOIN questions ON answers.question_id = questions.id " +
            "JOIN users ON answers.user_id = users.id " +
            "LEFT JOIN tags ON questions.tag_id = tags.id " +
            "WHERE answers.question_id = ? AND answers.is_deleted = 0";
    public static final String ADD_ANSWER_SQL = "INSERT INTO answers" +
            "(text, creation_date, question_id, user_id) VALUES(?,?,?,?)";
    public static final String GET_ANSWER_BY_ID = "SELECT * FROM answers " +
            "JOIN questions ON answers.question_id = questions.id " +
            "JOIN tags ON questions.tag_id = tags.id " +
            "JOIN users ON questions.user_id = users.id WHERE answers.id = ?";

    public static final String GET_ALL_TAGS_SQL = "SELECT * FROM tags";

    public static final String ADD_TAG_SQL = "INSERT INTO tags" +
            "(name, language) VALUES(?, 'ru')";
    public static final String ADD_TAG_SQL_EN = "INSERT INTO tags" +
            "(name, language) VALUES(?, 'en')";

    public static final String DELETE_TAG_SQL = "UPDATE tags " +
            "SET is_deleted = 1 WHERE id = ?";

    public static final String GET_TAG_BY_NAME = "SELECT * FROM tags " +
            "WHERE name = ?";

    public static final String GET_TAGS_FROM_COUNT = "SELECT * FROM tags "  +
            "WHERE language = 'ru' AND is_deleted = 0 LIMIT ?, ?";
    public static final String GET_TAGS_FROM_COUNT_EN = "SELECT * FROM tags "  +
            "WHERE language = 'en' AND is_deleted = 0 LIMIT ?, ?";

    public static final String GET_TAG_BY_ID = "SELECT * FROM tags " +
            "WHERE id = ?";


    public static final String GET_USER_BY_EMAIL_SQL = "SELECT * FROM users " +
            "WHERE email=?";
    public static final String ADD_USER_SQL = "INSERT INTO users" +
            "(email, nickname, password, registration_date) VALUES(?,?,?,?)";

    public static final String GET_USER_BY_ID = "SELECT * FROM users " +
            "WHERE id = ?";

    public static final String UPDATE_USER_BY_ID = "UPDATE users " +
            "SET email=?, password=?, real_name = ?, nickname=?, location=? WHERE id = ?";

}