package by.tc.xml_parser.bean;

import java.io.Serializable;
import java.util.List;

public class WebApp implements Serializable {
    private static final long serialVersionUID=1L;

    private List<WelcomeFileList> welcomeFiles;
    private List<Filter> filters;
    private List<FilterMapping> filterMappings;
    private List<Listener> listeners;
    private List<Servlet> servlets;
    private List<ServletMapping> servletMappings;
    private List<ErrorPage> errorPages;
    private List<DisplayName> displayNames;

    private String id;
    private String version;

    public List<WelcomeFileList> getWelcomeFiles() {
        return welcomeFiles;
    }

    public void setWelcomeFiles(List<WelcomeFileList> welcomeFiles) {
        this.welcomeFiles = welcomeFiles;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public List<FilterMapping> getFilterMappings() {
        return filterMappings;
    }

    public void setFilterMappings(List<FilterMapping> filterMappings) {
        this.filterMappings = filterMappings;
    }

    public List<Listener> getListeners() {
        return listeners;
    }

    public void setListeners(List<Listener> listeners) {
        this.listeners = listeners;
    }

    public List<Servlet> getServlets() {
        return servlets;
    }

    public void setServlets(List<Servlet> servlets) {
        this.servlets = servlets;
    }

    public List<ServletMapping> getServletMappings() {
        return servletMappings;
    }

    public void setServletMappings(List<ServletMapping> servletMappings) {
        this.servletMappings = servletMappings;
    }

    public List<ErrorPage> getErrorPages() {
        return errorPages;
    }

    public void setErrorPages(List<ErrorPage> errorPages) {
        this.errorPages = errorPages;
    }

    public List<DisplayName> getDisplayNames() {
        return displayNames;
    }

    public void setDisplayNames(List<DisplayName> displayNames) {
        this.displayNames = displayNames;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebApp webApp = (WebApp) o;

        if (welcomeFiles != null ? !welcomeFiles.equals(webApp.welcomeFiles) : webApp.welcomeFiles != null)
            return false;
        if (filters != null ? !filters.equals(webApp.filters) : webApp.filters != null) return false;
        if (filterMappings != null ? !filterMappings.equals(webApp.filterMappings) : webApp.filterMappings != null)
            return false;
        if (listeners != null ? !listeners.equals(webApp.listeners) : webApp.listeners != null)
            return false;
        if (servlets != null ? !servlets.equals(webApp.servlets) : webApp.servlets != null) return false;
        if (servletMappings != null ? !servletMappings.equals(webApp.servletMappings) : webApp.servletMappings != null)
            return false;
        if (errorPages != null ? !errorPages.equals(webApp.errorPages) : webApp.errorPages != null)
            return false;
        if (displayNames != null ? !displayNames.equals(webApp.displayNames) : webApp.displayNames != null)
            return false;
        if (id != null ? !id.equals(webApp.id) : webApp.id != null) return false;
        return version != null ? version.equals(webApp.version) : webApp.version == null;

    }

    @Override
    public int hashCode() {
        int result = welcomeFiles != null ? welcomeFiles.hashCode() : 0;
        result = 31 * result + (filters != null ? filters.hashCode() : 0);
        result = 31 * result + (filterMappings != null ? filterMappings.hashCode() : 0);
        result = 31 * result + (listeners != null ? listeners.hashCode() : 0);
        result = 31 * result + (servlets != null ? servlets.hashCode() : 0);
        result = 31 * result + (servletMappings != null ? servletMappings.hashCode() : 0);
        result = 31 * result + (errorPages != null ? errorPages.hashCode() : 0);
        result = 31 * result + (displayNames != null ? displayNames.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WebApp{" +
                "welcomeFiles=" + welcomeFiles +
                ", filters=" + filters +
                ", filterMappings=" + filterMappings +
                ", listeners=" + listeners +
                ", servlets=" + servlets +
                ", servletMappings=" + servletMappings +
                ", errorPages=" + errorPages +
                ", displayNames=" + displayNames +
                ", id='" + id + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
