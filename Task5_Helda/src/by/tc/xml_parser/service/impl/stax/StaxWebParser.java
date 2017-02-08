package by.tc.xml_parser.service.impl.stax;

import by.tc.xml_parser.bean.*;
import by.tc.xml_parser.service.Parser;
import by.tc.xml_parser.service.exception.ServiceException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StaxWebParser implements Parser{
    private static XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    private XMLStreamReader xmlReader;
    private WebTagName tag;
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

    private static final String WEB_APP_ATTR_ID = "id";
    private static final String WEB_APP_ATTR_VERSION = "version";

    @Override
    public WebApp parse(String filePath) throws ServiceException {
        if (filePath == null || filePath.isEmpty()){
            throw new ServiceException("Incorrect filePath");
        }
        WebApp webApp;
        try {
            InputStream inputStream = new FileInputStream(filePath);
            xmlReader = xmlInputFactory.createXMLStreamReader(inputStream);
        } catch (XMLStreamException  | FileNotFoundException e) {
            throw new ServiceException(e);
        }
        webApp = webAppInitProcess();
        return webApp;
    }

    private WebApp webAppInitProcess() throws ServiceException{
        try {
            while (xmlReader.hasNext()){
                int type = xmlReader.next();
                switch (type){
                    case XMLStreamConstants.START_ELEMENT: {
                        startElement();
                        break;
                    }
                    case XMLStreamConstants.CHARACTERS: {
                        String text = xmlReader.getText().trim();
                        if (text.isEmpty()){
                            break;
                        }
                        characters(text);
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {
                        endElement();
                        break;
                    }

                }
            }
        } catch (XMLStreamException e) {
            throw new ServiceException("Error during parsing procedure", e);
        }
        return webApp;
    }

    private void startElement(){
        tag = WebTagName.getWebTagName(xmlReader.getLocalName());
        switch (tag){
            case WEB_APP: {
                webApp = new WebApp();
                webApp.setId(xmlReader.getAttributeValue(null, WEB_APP_ATTR_ID));
                webApp.setVersion(xmlReader.getAttributeValue(null, WEB_APP_ATTR_VERSION));
                break;
            }
            case DISPLAY_NAME: {
                displayName = new DisplayName();
                break;
            }
            case WELCOME_FILE_LIST: {
                welcomeFiles = new WelcomeFileList();
                break;
            }
            case FILTER: {
                filter = new Filter();
                break;
            }
            case INIT_PARAM: {
                initParam = new InitParam();
                break;
            }
            case FILTER_MAPPING: {
                filterMapping = new FilterMapping();
                break;
            }
            case LISTENER: {
                listener = new Listener();
                break;
            }
            case SERVLET: {
                servlet = new Servlet();
                break;
            }
            case SERVLET_MAPPING: {
                servletMapping = new ServletMapping();
                break;
            }
            case ERROR_PAGE: {
                errorPage = new ErrorPage();
                break;
            }
        }
    }

    private void endElement(){
        tag = WebTagName.getWebTagName(xmlReader.getLocalName());
        switch (tag){
            case DISPLAY_NAME:{
                webApp.getDisplayNames().add(displayName);
                displayName = null;
                break;
            }
            case WELCOME_FILE_LIST: {
                welcomeFiles.setWelcomeFile(tempWelcomeFiles);
                webApp.getWelcomeFiles().add(welcomeFiles);
                welcomeFiles = null;
                break;
            }
            case FILTER: {
                filter.setInitParams(initParams);
                webApp.getFilters().add(filter);
                filter = null;
                break;
            }
            case INIT_PARAM: {
                initParams.add(initParam);
                initParam = null;
                break;
            }
            case FILTER_MAPPING: {
                webApp.getFilterMappings().add(filterMapping);
                filterMapping = null;
                break;
            }
            case LISTENER: {
                webApp.getListeners().add(listener);
                listener = null;
                break;
            }
            case SERVLET: {
                webApp.getServlets().add(servlet);
                servlet = null;
                break;
            }
            case SERVLET_MAPPING: {
                webApp.getServletMappings().add(servletMapping);
                servletMapping = null;
                break;
            }
            case ERROR_PAGE: {
                webApp.getErrorPages().add(errorPage);
                errorPage = null;
                break;
            }
        }
    }

    private void characters(String text){
        switch (tag){
            case DISPLAY_NAME:{
                displayName.setDescription(text);
                break;
            }
            case WELCOME_FILE: {
                tempWelcomeFiles.add(text);
                break;
            }
            case FILTER_NAME: {
                if (filter!=null){
                    filter.setFilterName(text);
                } else {
                    filterMapping.setFilterName(text);
                }
                break;
            }
            case FILTER_CLASS: {
                filter.setFilterClass(text);
                break;
            }
            case PARAM_NAME: {
                initParam.setParamName(text);
                break;
            }
            case PARAM_VALUE: {
                initParam.setParamValue(text);
                break;
            }
            case URL_PATTERN: {
                if (filterMapping != null){
                    filterMapping.setUrlPattern(text);
                } else {
                    servletMapping.setUrlPattern(text);
                }
                break;
            }
            case DISPATCHER: {
                filterMapping.setDispatcher(text);
                break;
            }
            case LISTENER_CLASS: {
                listener.setListenerClass(text);
                break;
            }
            case SERVLET_NAME: {
                if (servlet != null){
                    servlet.setServletName(text);
                } else {
                    servletMapping.setServletName(text);
                }
                break;
            }
            case SERVLET_CLASS: {
                servlet.setServletClass(text);
                break;
            }
            case EXCEPTION_TYPE: {
                errorPage.setExceptionType(text);
                break;
            }
            case LOCATION: {
                errorPage.setLocation(text);
                break;
            }
            case ERROR_CODE: {
                errorPage.setErrorCode(Integer.parseInt(text));
                break;
            }
        }
    }
}
