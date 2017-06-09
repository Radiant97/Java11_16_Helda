package by.tc.like_it.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String header;
    private String text;
    private Date creationDate;
    private User user;

    private Tag tag;

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != question.id) return false;
        if (!header.equals(question.header)) return false;
        if (!text.equals(question.text)) return false;
        if (!creationDate.equals(question.creationDate)) return false;
        if (!user.equals(question.user)) return false;
        return tag != null ? tag.equals(question.tag) : question.tag == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + header.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                ", user=" + user +
                ", tag=" + tag +
                '}';
    }
}
