package by.tc.xml_parser.service.impl.sax;

import by.tc.xml_parser.bean.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxWebHandler extends DefaultHandler {
    private static final String WEB_APP_ATTR_ID = "id";
    private static final String WEB_APP_ATTR_VERSION = "version";

    private WebApp webApp;
    private List<String> tempWelcomeFiles = new ArrayList<>();
    private List<InitParam> initParams = new ArrayList<>();
    private DisplayName displayName;
    private WelcomeFileList welcomeFiles;
    private ErrorPage errorPage;
    private Filter filter;
    private FilterMapping filterMapping;
    private InitParam initParam;
    private Listener listener;
    private Servlet servlet;
    private ServletMapping servletMapping;

    private StringBuilder text;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        text = new StringBuilder();

        WebTagName tagName = WebTagName.getWebTagName(qName);
        switch (tagName) {
            case WEB_APP:
                webApp = new WebApp();
                webApp.setId(attributes.getValue(WEB_APP_ATTR_ID));
                webApp.setVersion(attributes.getValue(WEB_APP_ATTR_VERSION));
                break;
            case DISPLAY_NAME:
                displayName = new DisplayName();
                break;
            case WELCOME_FILE_LIST:
                welcomeFiles = new WelcomeFileList();
                break;
            case FILTER:
                filter = new Filter();
                break;
            case FILTER_MAPPING:
                filterMapping = new FilterMapping();
                break;
            case LISTENER:
                listener = new Listener();
                break;
            case SERVLET:
                servlet = new Servlet();
                break;
            case SERVLET_MAPPING:
                servletMapping = new ServletMapping();
                break;
            case ERROR_PAGE:
                errorPage = new ErrorPage();
                break;
            case INIT_PARAM:
                initParam = new InitParam();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        WebTagName tagName = WebTagName.getWebTagName(qName);
        switch (tagName){
            case DISPLAY_NAME:
                displayName.setDescription(text.toString());
                webApp.getDisplayNames().add(displayName);
                displayName = null;
                break;
            case WELCOME_FILE:
                tempWelcomeFiles.add(text.toString());
                break;
            case WELCOME_FILE_LIST:
                welcomeFiles.setWelcomeFile(tempWelcomeFiles);
                webApp.getWelcomeFiles().add(welcomeFiles);
                welcomeFiles = null;
                break;
            case FILTER_NAME:
                if (filter != null){
                    filter.setFilterName(text.toString());
                } else {
                    filterMapping.setFilterName(text.toString());
                }
                break;
            case FILTER_CLASS:
                filter.setFilterClass(text.toString());
                break;
            case PARAM_NAME:
                initParam.setParamName(text.toString());
                break;
            case PARAM_VALUE:
                initParam.setParamValue(text.toString());
                break;
            case INIT_PARAM:
                initParams.add(initParam);
                initParam = null;
                break;
            case FILTER:
                filter.setInitParams(initParams);
                webApp.getFilters().add(filter);
                initParams = null;
                filter = null;
                break;
            case URL_PATTERN:
                if (filterMapping != null){
                    filterMapping.setUrlPattern(text.toString());
                } else {
                    servletMapping.setUrlPattern(text.toString());
                }
                break;
            case DISPATCHER:
                filterMapping.setDispatcher(text.toString());
                break;
            case FILTER_MAPPING:
                webApp.getFilterMappings().add(filterMapping);
                filterMapping = null;
                break;
            case LISTENER_CLASS:
                listener.setListenerClass(text.toString());
                break;
            case LISTENER:
                webApp.getListeners().add(listener);
                listener = null;
                break;
            case SERVLET_NAME:
                if (servlet != null) {
                    servlet.setServletName(text.toString());
                }
                else {
                    servletMapping.setServletName(text.toString());
                }
                break;
            case SERVLET_CLASS:
                servlet.setServletClass(text.toString());
                break;
            case SERVLET:
                webApp.getServlets().add(servlet);
                servlet = null;
                break;
            case SERVLET_MAPPING:
                webApp.getServletMappings().add(servletMapping);
                servletMapping = null;
                break;
            case EXCEPTION_TYPE:
                errorPage.setExceptionType(text.toString());
                break;
            case LOCATION:
                errorPage.setLocation(text.toString());
                break;
            case ERROR_CODE:
                errorPage.setErrorCode(Integer.parseInt(text.toString()));
                break;
            case ERROR_PAGE:
                webApp.getErrorPages().add(errorPage);
                errorPage = null;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text.append(ch, start, length);
    }

    public WebApp getWebApp() {
        return webApp;
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.err.println("WARNING: line " + e.getLineNumber() + " : " + e.getMessage());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.err.println("ERROR: line " + e.getLineNumber() + " : " + e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.err.println("FATAL: line " + e.getLineNumber() + " : " + e.getMessage());
    }
}
