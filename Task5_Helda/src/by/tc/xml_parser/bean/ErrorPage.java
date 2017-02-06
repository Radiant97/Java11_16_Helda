package by.tc.xml_parser.bean;

import java.io.Serializable;

public class ErrorPage implements Serializable {
    private static final long serialVersionUID=1L;
    private String exceptionType;
    private String location;
    private int errorCode;

    public ErrorPage() {
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorPage errorPage = (ErrorPage) o;

        if (errorCode != errorPage.errorCode) return false;
        if (exceptionType != null ? !exceptionType.equals(errorPage.exceptionType) : errorPage.exceptionType != null)
            return false;
        return location != null ? location.equals(errorPage.location) : errorPage.location == null;

    }

    @Override
    public int hashCode() {
        int result = exceptionType != null ? exceptionType.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + errorCode;
        return result;
    }

    @Override
    public String toString() {
        return "ErrorPage{" +
                "exceptionType='" + exceptionType + '\'' +
                ", location='" + location + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}
