package by.tc.xml_parser.service.factory;

import by.tc.xml_parser.service.Parser;
import by.tc.xml_parser.service.impl.dom.DomWebParser;

public class ServiceFactory {
    private static final ServiceFactory instance=new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private final Parser saxWebParser = new SaxWebParser();
    private final Parser domWebParser = new DomWebParser();
    private final Parser staxWebParser = new StaxWebParser();

    public Parser getSaxWebParser() {
        return saxWebParser;
    }

    public Parser getDomWebParser() {
        return domWebParser;
    }

    public Parser getStaxWebParser() {
        return staxWebParser;
    }
}
