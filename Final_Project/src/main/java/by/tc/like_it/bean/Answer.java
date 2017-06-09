package by.tc.like_it.bean;

import java.io.Serializable;
import java.util.Date;

public class Answer implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;
    private String text;
    private Date creation_date;
    private Question question;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (id != answer.id) return false;
        if (text != null ? !text.equals(answer.text) : answer.text != null) return false;
        if (creation_date != null ? !creation_date.equals(answer.creation_date) : answer.creation_date != null)
            return false;
        if (question != null ? !question.equals(answer.question) : answer.question != null) return false;
        return user != null ? user.equals(answer.user) : answer.user == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (creation_date != null ? creation_date.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", creation_date=" + creation_date +
                ", question=" + question +
                ", user=" + user +
                '}';
    }
}
