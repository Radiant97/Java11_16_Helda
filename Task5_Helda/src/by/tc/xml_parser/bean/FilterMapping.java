package by.tc.xml_parser.bean;

import java.io.Serializable;

public class FilterMapping implements Serializable{
    private static final long serialVersionUID=1L;
    private String filterName;
    private String urlPattern;
    private String dispatcher;

    public FilterMapping() {
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilterMapping that = (FilterMapping) o;

        if (filterName != null ? !filterName.equals(that.filterName) : that.filterName != null) return false;
        if (urlPattern != null ? !urlPattern.equals(that.urlPattern) : that.urlPattern != null) return false;
        return dispatcher != null ? dispatcher.equals(that.dispatcher) : that.dispatcher == null;

    }

    @Override
    public int hashCode() {
        int result = filterName != null ? filterName.hashCode() : 0;
        result = 31 * result + (urlPattern != null ? urlPattern.hashCode() : 0);
        result = 31 * result + (dispatcher != null ? dispatcher.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FilterMapping{" +
                "filterName='" + filterName + '\'' +
                ", urlPattern='" + urlPattern + '\'' +
                ", dispatcher='" + dispatcher + '\'' +
                '}';
    }
}
