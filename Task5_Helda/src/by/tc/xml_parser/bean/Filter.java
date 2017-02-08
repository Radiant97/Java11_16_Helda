package by.tc.xml_parser.bean;

import java.io.Serializable;
import java.util.List;

public class Filter  implements Serializable {
    private static final long serialVersionUID=1L;
    private String filterName;
    private String filterClass;
    private List<InitParam> initParams;

    public Filter() {
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterClass() {
        return filterClass;
    }

    public void setFilterClass(String filterClass) {
        this.filterClass = filterClass;
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

        Filter filter = (Filter) o;

        if (filterName != null ? !filterName.equals(filter.filterName) : filter.filterName != null) return false;
        if (filterClass != null ? !filterClass.equals(filter.filterClass) : filter.filterClass != null) return false;
        return initParams != null ? initParams.equals(filter.initParams) : filter.initParams == null;

    }

    @Override
    public int hashCode() {
        int result = filterName != null ? filterName.hashCode() : 0;
        result = 31 * result + (filterClass != null ? filterClass.hashCode() : 0);
        result = 31 * result + (initParams != null ? initParams.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "filterName='" + filterName + '\'' +
                ", filterClass='" + filterClass + '\'' +
                ", initParams=" + initParams +
                '}';
    }
}
