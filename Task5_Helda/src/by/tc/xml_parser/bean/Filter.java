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
}
