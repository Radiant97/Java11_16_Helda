package by.tc.xml_parser.bean;

import java.io.Serializable;

public class ServletMapping implements Serializable {
    private static final long serialVersionUID=1L;

    private String servletName;
    private String urlPattern;

    public ServletMapping() {
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServletMapping that = (ServletMapping) o;

        if (servletName != null ? !servletName.equals(that.servletName) : that.servletName != null) return false;
        return urlPattern != null ? urlPattern.equals(that.urlPattern) : that.urlPattern == null;

    }

    @Override
    public int hashCode() {
        int result = servletName != null ? servletName.hashCode() : 0;
        result = 31 * result + (urlPattern != null ? urlPattern.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServletMapping{" +
                "servletName='" + servletName + '\'' +
                ", urlPattern='" + urlPattern + '\'' +
                '}';
    }
}
