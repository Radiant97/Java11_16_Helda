package by.tc.xml_parser.bean;

import java.io.Serializable;
import java.util.List;

public class Servlet implements Serializable {
    private static final long serialVersionUID=1L;

    private String servletName;
    private String servletClass;
    private List<InitParam> initParams;

    public Servlet() {
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getServletClass() {
        return servletClass;
    }

    public void setServletClass(String servletClass) {
        this.servletClass = servletClass;
    }

    public List<InitParam> getInitParams() {
        return initParams;
    }

    public void setInitParams(List<InitParam> initParams) {
        this.initParams = initParams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Servlet servlet = (Servlet) o;

        if (servletName != null ? !servletName.equals(servlet.servletName) : servlet.servletName != null) return false;
        if (servletClass != null ? !servletClass.equals(servlet.servletClass) : servlet.servletClass != null)
            return false;
        return initParams != null ? initParams.equals(servlet.initParams) : servlet.initParams == null;

    }

    @Override
    public int hashCode() {
        int result = servletName != null ? servletName.hashCode() : 0;
        result = 31 * result + (servletClass != null ? servletClass.hashCode() : 0);
        result = 31 * result + (initParams != null ? initParams.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Servlet{" +
                "servletName='" + servletName + '\'' +
                ", servletClass='" + servletClass + '\'' +
                ", initParams=" + initParams +
                '}';
    }
}
