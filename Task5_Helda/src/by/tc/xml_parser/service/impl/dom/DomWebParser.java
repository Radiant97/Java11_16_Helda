package by.tc.xml_parser.service.impl.dom;


import by.tc.xml_parser.bean.*;
import by.tc.xml_parser.service.Parser;
import by.tc.xml_parser.service.exception.ServiceException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomWebParser implements Parser{
    private static final String WEB_APP_ATTR_ID = "id";
    private static final String WEB_APP_ATTR_VERSION = "version";

    @Override
    public WebApp parse(String filePath) throws ServiceException {
        if (filePath == null || filePath.isEmpty()){
            throw new ServiceException("Incorrect filePath");
        }

        InputSource is = new InputSource(filePath);
        DOMParser domParser = new DOMParser();
        try {
            domParser.parse(is);
        } catch (IOException | SAXException e) {
            throw new ServiceException("Error during parsing procedure.", e);
        }

        Document document = domParser.getDocument();
        Element root = document.getDocumentElement();

        WebApp webApp = new WebApp();
        webApp.setId(root.getAttribute(WEB_APP_ATTR_ID));
        webApp.setVersion(root.getAttribute(WEB_APP_ATTR_VERSION));

        settingProcess(webApp, root);

        return webApp;
    }

    private void settingProcess(WebApp webApp, Element root){
        welcomeFileListSettingProcess(webApp,root);
        filterSettingProcess(webApp, root);
        filterMappingSettingProcess(webApp, root);
        listenerSettingProcess(webApp, root);
        servletSettingProcess(webApp, root);
        servletMappingSettingProcess(webApp, root);
        errorPageSettingProcess(webApp,root);
        displayNameSettingProcess(webApp,root);
    }

    private Element getSingleChild(Element element,String childName){
        NodeList nodeList = element.getElementsByTagName(childName);
        return (Element) nodeList.item(0);
    }

    private void welcomeFileListSettingProcess(WebApp webApp, Element root){
        WelcomeFileList welcomeFileList;
        NodeList welcomeFileNode = root.getElementsByTagName(WebTagName.WELCOME_FILE_LIST.toString());
        List<String> welcomeFiles = new ArrayList<>();

        for (int i = 0; i < welcomeFileNode.getLength(); i++) {
            welcomeFileList = new WelcomeFileList();
            Element welcomeFileListElement = (Element) welcomeFileNode.item(i);

            List<Element> elements = getChildren(welcomeFileListElement,WebTagName.WELCOME_FILE.toString());
            for (Element e : elements){
                welcomeFiles.add(e.getTextContent().trim());
            }
            welcomeFileList.setWelcomeFile(welcomeFiles);
            webApp.getWelcomeFiles().add(welcomeFileList);
        }
    }

    private List<Element> getChildren(Element element, String childName){
        NodeList nodeList = element.getElementsByTagName(childName);
        List<Element> elements = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++){
            Element child = (Element) nodeList.item(i);
            elements.add(child);
        }
        return elements;
    }

    private void filterSettingProcess(WebApp webApp, Element root){
        Filter filter;
        NodeList filterNodeList = root.getElementsByTagName(WebTagName.FILTER.toString());

        for (int i = 0; i < filterNodeList.getLength(); i++) {
            filter = new Filter();
            Element element = (Element) filterNodeList.item(i);
            filter.setFilterName(getSingleChild(element,WebTagName.FILTER_NAME.toString()).getTextContent().trim());
            filter.setFilterClass(getSingleChild(element,WebTagName.FILTER_CLASS.toString()).getTextContent().trim());

            filter.setInitParams(getInitParamList(element));
            webApp.getFilters().add(filter);
        }
    }

    private void filterMappingSettingProcess(WebApp webApp, Element root){
        FilterMapping filterMapping;
        NodeList filterMappingNode = root.getElementsByTagName(WebTagName.FILTER_MAPPING.toString());

        for (int i = 0 ; i < filterMappingNode.getLength(); i++) {
            filterMapping = new FilterMapping();
            Element element = (Element) filterMappingNode.item(i);
            filterMapping.setFilterName(getSingleChild(element, WebTagName.FILTER_NAME.toString()).getTextContent().trim());
            filterMapping.setUrlPattern(getSingleChild(element, WebTagName.URL_PATTERN.toString()).getTextContent().trim());
            filterMapping.setDispatcher(getSingleChild(element, WebTagName.DISPATCHER.toString()).getTextContent().trim());

            webApp.getFilterMappings().add(filterMapping);
        }
    }

    private void listenerSettingProcess(WebApp webApp, Element root){
        Listener listener;
        NodeList listenerNode = root.getElementsByTagName(WebTagName.LISTENER.toString());

        for (int i = 0; i < listenerNode.getLength(); i++) {
            listener = new Listener();
            Element element = (Element) listenerNode.item(i);
            listener.setListenerClass(getSingleChild(element, WebTagName.LISTENER_CLASS.toString()).getTextContent().trim());
            webApp.getListeners().add(listener);
        }
    }

    private void servletSettingProcess(WebApp webApp, Element root){
        Servlet servlet;
        NodeList servletNode = root.getElementsByTagName(WebTagName.SERVLET.toString());

        for (int i = 0; i < servletNode.getLength(); i++) {
            servlet = new Servlet();
            Element element = (Element) servletNode.item(i);
            servlet.setServletName(getSingleChild(element, WebTagName.SERVLET_NAME.toString()).getTextContent().trim());
            servlet.setServletClass(getSingleChild(element, WebTagName.SERVLET_CLASS.toString()).getTextContent().trim());

            servlet.setInitParams(getInitParamList(element));
            webApp.getServlets().add(servlet);
        }
    }

    private void servletMappingSettingProcess(WebApp webApp, Element root){
        ServletMapping servletMapping;
        NodeList servletNode = root.getElementsByTagName(WebTagName.SERVLET_MAPPING.toString());

        for (int i = 0; i < servletNode.getLength(); i++) {
            servletMapping = new ServletMapping();
            Element element = (Element) servletNode.item(i);
            servletMapping.setServletName(getSingleChild(element, WebTagName.SERVLET_NAME.toString()).getTextContent().trim());
            servletMapping.setUrlPattern(getSingleChild(element, WebTagName.URL_PATTERN.toString()).getTextContent().trim());

            webApp.getServletMappings().add(servletMapping);
        }
    }

    private void errorPageSettingProcess(WebApp webApp, Element root){
        ErrorPage errorPage;
        NodeList errorPageNode = root.getElementsByTagName(WebTagName.ERROR_PAGE.toString());

        for (int i = 0; i < errorPageNode.getLength(); i++) {
            errorPage = new ErrorPage();
            Element errorPageElement = (Element) errorPageNode.item(i);
            Element exceptionTypeElement = getSingleChild(errorPageElement, WebTagName.EXCEPTION_TYPE.toString());
            if (exceptionTypeElement != null){
                errorPage.setExceptionType(exceptionTypeElement.getTextContent().trim());
            }else {
                errorPage.setErrorCode(Integer.parseInt(getSingleChild(errorPageElement, WebTagName.ERROR_CODE.toString()).getTextContent().trim()));
            }
            errorPage.setLocation(getSingleChild(errorPageElement, WebTagName.LOCATION.toString()).getTextContent().trim());
            webApp.getErrorPages().add(errorPage);
        }
    }

    private void displayNameSettingProcess(WebApp webApp, Element root){
        DisplayName displayName;
        NodeList displayNameNode = root.getElementsByTagName(WebTagName.DISPLAY_NAME.toString());

        for (int i = 0; i < displayNameNode.getLength(); i++) {
            displayName = new DisplayName();
            Element element = (Element) displayNameNode.item(i);
            displayName.setDescription(element.getTextContent().trim());
            webApp.getDisplayNames().add(displayName);
        }
    }

    private List<InitParam> getInitParamList(Element parentElement){
        List<InitParam> initParams = new ArrayList<>();
        List<Element> elements = getChildren(parentElement, WebTagName.INIT_PARAM.toString());

        for (Element e : elements){
            InitParam initParam = new InitParam();
            initParam.setParamName(getSingleChild(e, WebTagName.PARAM_NAME.toString()).getTextContent().trim());
            initParam.setParamValue(getSingleChild(e, WebTagName.PARAM_VALUE.toString()).getTextContent().trim());
            initParams.add(initParam);
        }
        return initParams;
    }
}
