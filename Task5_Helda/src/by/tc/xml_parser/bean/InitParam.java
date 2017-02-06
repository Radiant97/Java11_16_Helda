package by.tc.xml_parser.bean;

import java.io.Serializable;

public class InitParam implements Serializable {
    private static final long serialVersionUID=1L;
    private String paramName;
    private String paramValue;

    public InitParam() {
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InitParam initParam = (InitParam) o;

        if (paramName != null ? !paramName.equals(initParam.paramName) : initParam.paramName != null) return false;
        return paramValue != null ? paramValue.equals(initParam.paramValue) : initParam.paramValue == null;

    }

    @Override
    public int hashCode() {
        int result = paramName != null ? paramName.hashCode() : 0;
        result = 31 * result + (paramValue != null ? paramValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InitParam{" +
                "paramName='" + paramName + '\'' +
                ", paramValue='" + paramValue + '\'' +
                '}';
    }
}
