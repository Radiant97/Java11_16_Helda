package by.tc.xml_parser.service;

import by.tc.xml_parser.bean.WebApp;
import by.tc.xml_parser.service.exception.ServiceException;

public interface Parser {
    WebApp parse(String filePath) throws ServiceException;
}
