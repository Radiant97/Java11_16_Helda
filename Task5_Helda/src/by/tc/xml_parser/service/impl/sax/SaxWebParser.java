package by.tc.xml_parser.service.impl.sax;

import by.tc.xml_parser.bean.WebApp;
import by.tc.xml_parser.service.Parser;
import by.tc.xml_parser.service.exception.ServiceException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;


public class SaxWebParser implements Parser{
    private SaxWebHandler saxWebHandler;

    @Override
    public WebApp parse(String filePath) throws ServiceException {
        InputSource inputSource = new InputSource(filePath);
        try {
            saxWebHandler = new SaxWebHandler();

            XMLReader xmlReader= XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(saxWebHandler);
            xmlReader.parse(inputSource);
        } catch (SAXException | IOException e) {
            throw new ServiceException("Error during parsing procedure.", e);
        }
        return  saxWebHandler.getWebApp();
    }
}
