package by.tc.xml_parser.view;


import by.tc.xml_parser.bean.WebApp;
import by.tc.xml_parser.service.Parser;
import by.tc.xml_parser.service.exception.ServiceException;
import by.tc.xml_parser.service.factory.ServiceFactory;

public class Runner {
    public static void main(String[] args){
        String filePath="src/by/tc/xml_parser/resource/web.xml";
        ServiceFactory sf = ServiceFactory.getInstance();
        Parser parser = sf.getDomWebParser();
        WebApp webApp = null;
        try {
            webApp = parser.parse(filePath);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println(webApp.toString());
    }
}
