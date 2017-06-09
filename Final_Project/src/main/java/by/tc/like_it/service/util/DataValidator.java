package by.tc.like_it.service.util;

import by.tc.like_it.service.exception.ServiceWrongDataException;

public class DataValidator {
    private static final int MAX_QUESTION_HEADER_LENGTH = 100;
    private static final int MAX_QUESTION_TEXT_LENGTH = 1000;
    private static final int MAX_ANSWER_TEXT_LENGTH = 1000;
    private static final int MAX_TAG_NAME_LENGTH = 30;

    private static final String EMAIL_PATTERN = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String PASSWORD_PATTERN = "[A-z\\d]{6,}";
    private static final String NICKNAME_PATTERN = "[A-z_\\d]{5,20}";
    private static final String REAL_NAME_PATTERN = "[a-Z_]{2,60}";
    private static final String LOCATION_PATTERN = "[a-Z_-\\d]{2,50}";

    public static void validateEmail(String email) throws ServiceWrongDataException {
        if (email == null || email.isEmpty() || !email.matches(EMAIL_PATTERN)) {
            throw new ServiceWrongDataException("Invalid email.");
        }
    }
    public static void validatePassword(String password) throws ServiceWrongDataException {
        if (password == null || password.isEmpty() || !password.matches(PASSWORD_PATTERN)) {
            throw new ServiceWrongDataException("Invalid password.");
        }
    }
    public static void validatePassword(String password, String confirmPassword) throws ServiceWrongDataException {
        validatePassword(password);
        validatePassword(confirmPassword);
        if (!password.equals(confirmPassword)) {
            throw new ServiceWrongDataException("Passwords do not match.");
        }
    }
    public static void validateNickname(String nickName) throws ServiceWrongDataException {
        if (nickName == null || nickName.isEmpty() || !nickName.matches(NICKNAME_PATTERN)) {
            throw new ServiceWrongDataException("Invalid nickname.");
        }
    }

    public static void validateId(int id) throws ServiceWrongDataException {
        if (id <= 0) {
            throw new ServiceWrongDataException("Invalid id.");
        }
    }
    public static void validateQuestionHeader(String header) throws ServiceWrongDataException {
        if (header == null ||  header.isEmpty()  || header.length() > MAX_QUESTION_HEADER_LENGTH) {
            throw new ServiceWrongDataException("Invalid question header.");
        }
    }
    public static void validateQuestionText(String text) throws ServiceWrongDataException {
        if (text == null || text.isEmpty() || text.length() > MAX_QUESTION_TEXT_LENGTH) {
            throw new ServiceWrongDataException("Invalid question text.");
        }
    }
    public static void validateAnswerText(String text) throws ServiceWrongDataException {
        if (text == null || text.isEmpty() || text.length() > MAX_ANSWER_TEXT_LENGTH) {
            throw new ServiceWrongDataException("Invalid question text.");
        }
    }
    public static void validateTagName(String name) throws ServiceWrongDataException {
        if (name == null ||name.isEmpty() || name.trim().length() > MAX_TAG_NAME_LENGTH ) {
            throw new ServiceWrongDataException("Invalid tag name.");
        }
    }
    public static void validateElementCount(int val) throws ServiceWrongDataException {
        if (val <= 0) {
            throw new ServiceWrongDataException("Invalid element count.");
        }
    }
    public static void validateStartFromElement(int val) throws ServiceWrongDataException {
        if (val < 0) {
            throw new ServiceWrongDataException("Invalid value of 'start from'.");
        }
    }
    public static void validateRealName(String name) throws ServiceWrongDataException {
        if (!name.matches(REAL_NAME_PATTERN)) {
            throw new ServiceWrongDataException("Invalid real name.");
        }
    }
    public static void validateLocation(String location) throws ServiceWrongDataException {
        if (!location.matches(LOCATION_PATTERN)) {
            throw new ServiceWrongDataException("Invalid location.");
        }
    }
}
