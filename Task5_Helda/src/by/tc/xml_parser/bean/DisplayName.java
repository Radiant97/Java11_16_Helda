package by.tc.xml_parser.bean;

import java.io.Serializable;

public class DisplayName implements Serializable {
    private static final long serialVersionUID=1L;

    public DisplayName() {
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DisplayName that = (DisplayName) o;

        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        return description != null ? description.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "DisplayName{" +
                "description='" + description + '\'' +
                '}';
    }
}
