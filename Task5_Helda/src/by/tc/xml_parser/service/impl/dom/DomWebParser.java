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
        webApp.setId(root.getAttribute("id"));
        webApp.setVersion(root.getAttribute("version"));

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
        NodeList welcomeFileNode = root.getElementsByTagName("welcome-file-list");
        List<String> welcomeFiles = new ArrayList<>();

        for (int i = 0; i < welcomeFileNode.getLength(); i++) {
            welcomeFileList = new WelcomeFileList();
            Element welcomeFileListElement = (Element) welcomeFileNode.item(i);

            List<Element> elements = getChildren(welcomeFileListElement,"welcome-file");
            for (Element e : elements){
                welcomeFiles.add(e.getTextContent().trim());
            }
            welcomeFileList.setWelcomeFile(welcomeFiles);
            webApp.getWelcomeFileList().add(welcomeFileList);
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
        NodeList filterNodeList = root.getElementsByTagName("filter");

        for (int i = 0; i < filterNodeList.getLength(); i++) {
            filter = new Filter();
            Element element = (Element) filterNodeList.item(i);
            filter.setFilterName(getSingleChild(element,"filter-name").getTextContent().trim());
            filter.setFilterClass(getSingleChild(element,"filter-class").getTextContent().trim());

            filter.setInitParams(getInitParamList(element));
            webApp.getFilterList().add(filter);
        }
    }

    private void filterMappingSettingProcess(WebApp webApp, Element root){
        FilterMapping filterMapping;
        NodeList filterMappingNode = root.getElementsByTagName("filter-mapping");

        for (int i = 0 ; i < filterMappingNode.getLength(); i++) {
            filterMapping = new FilterMapping();
            Element element = (Element) filterMappingNode.item(i);
            filterMapping.setFilterName(getSingleChild(element,"filter-name").getTextContent().trim());
            filterMapping.setUrlPattern(getSingleChild(element,"url-pattern").getTextContent().trim());
            filterMapping.setDispatcher(getSingleChild(element,"dispatcher").getTextContent().trim());

            webApp.getFilterMappingList().add(filterMapping);
        }
    }

    private void listenerSettingProcess(WebApp webApp, Element root){
        Listener listener;
        NodeList listenerNode = root.getElementsByTagName("listener");

        for (int i = 0; i < listenerNode.getLength(); i++) {
            listener = new Listener();
            Element element = (Element) listenerNode.item(i);
            listener.setListenerClass(getSingleChild(element,"listener-class").getTextContent().trim());
            webApp.getListenerList().add(listener);
        }
    }

    private void servletSettingProcess(WebApp webApp, Element root){
        Servlet servlet;
        NodeList servletNode = root.getElementsByTagName("servlet");

        for (int i = 0; i < servletNode.getLength(); i++) {
            servlet = new Servlet();
            Element element = (Element) servletNode.item(i);
            servlet.setServletName(getSingleChild(element,"servlet-name").getTextContent().trim());
            servlet.setServletClass(getSingleChild(element,"servlet-class").getTextContent().trim());

            servlet.setInitParams(getInitParamList(element));
            webApp.getServletList().add(servlet);
        }
    }

    private void servletMappingSettingProcess(WebApp webApp, Element root){
        ServletMapping servletMapping;
        NodeList servletNode = root.getElementsByTagName("servlet-mapping");

        for (int i = 0; i < servletNode.getLength(); i++) {
            servletMapping = new ServletMapping();
            Element element = (Element) servletNode.item(i);
            servletMapping.setServletName(getSingleChild(element,"servlet-name").getTextContent().trim());
            servletMapping.setUrlPattern(getSingleChild(element,"url-pattern").getTextContent().trim());

            webApp.getServletMappingList().add(servletMapping);
        }
    }

    private void errorPageSettingProcess(WebApp webApp, Element root){
        ErrorPage errorPage;
        NodeList errorPageNode = root.getElementsByTagName("error-page");

        for (int i = 0; i < errorPageNode.getLength(); i++) {
            errorPage = new ErrorPage();
            Element errorPageElement = (Element) errorPageNode.item(i);
            Element exceptionTypeElement = getSingleChild(errorPageElement,"exception-type");
            if (exceptionTypeElement != null){
                errorPage.setExceptionType(exceptionTypeElement.getTextContent().trim());
            }else {
                errorPage.setErrorCode(Integer.parseInt(getSingleChild(errorPageElement,"error-code").getTextContent().trim()));
            }
            errorPage.setLocation(getSingleChild(errorPageElement,"location").getTextContent().trim());
            webApp.getErrorPageList().add(errorPage);
        }
    }

    private void displayNameSettingProcess(WebApp webApp, Element root){
        DisplayName displayName;
        NodeList displayNameNode = root.getElementsByTagName("display-name");

        for (int i = 0; i < displayNameNode.getLength(); i++) {
            displayName = new DisplayName();
            Element element = (Element) displayNameNode.item(i);
            displayName.setDescription(element.getTextContent().trim());
            webApp.getDisplayNameList().add(displayName);
        }
    }

    private List<InitParam> getInitParamList(Element parentElement){
        List<InitParam> initParams = new ArrayList<>();
        List<Element> elements = getChildren(parentElement,"init-param");

        for (Element e : elements){
            InitParam initParam = new InitParam();
            initParam.setParamName(getSingleChild(e,"param-name").getTextContent().trim());
            initParam.setParamValue(getSingleChild(e,"param-value").getTextContent().trim());
            initParams.add(initParam);
        }
        return initParams;
    }
}
