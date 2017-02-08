package by.tc.xml_parser.bean;

public enum WebTagName {
    WEB_APP("web-app"), DISPLAY_NAME("display-name"), FILTER("filter"),
    INIT_PARAM("init-param"), FILTER_MAPPING("filter-mapping"),
    LISTENER("listener"), SERVLET("servlet"),SERVLET_MAPPING("servlet-mapping"),
    ERROR_PAGE("error-page"), WELCOME_FILE("welcome-file"),
    WELCOME_FILE_LIST("welcome-file-list"), FILTER_NAME("filter-name"),
    FILTER_CLASS("filter-class"), PARAM_NAME("param-name"),
    PARAM_VALUE("param-value"), URL_PATTERN("url-pattern"),
    DISPATCHER("dispatcher"), LISTENER_CLASS("listener-class"),
    SERVLET_NAME("servlet-name"), SERVLET_CLASS("servlet-class"),
    EXCEPTION_TYPE("exception-type"), LOCATION("location"),
    ERROR_CODE("error-code");

    private final String strWebTagName;

    private WebTagName(final String strWebTagName){
        this.strWebTagName = strWebTagName;
    }

    @Override
    public String toString() {
        return this.strWebTagName;
    }

    public static WebTagName getWebTagName(String webTagName){
        return WebTagName.valueOf(webTagName.toUpperCase().replace("-", "_"));
    }
}
